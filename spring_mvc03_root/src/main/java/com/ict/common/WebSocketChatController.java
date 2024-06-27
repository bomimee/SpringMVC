package com.ict.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebSocketChatController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	private Map<String, ChatRoom> chatRooms = new HashMap<>();

	@GetMapping("/chat.do")
	public ModelAndView Chat() {
		return new ModelAndView("chat/chatting");
	}

	@GetMapping("/chat2.do")
	public ModelAndView Chat2() {
		return new ModelAndView("chat/chatting2");
	}
	
	@GetMapping("/chat3.do")
	public ModelAndView Chat3() {
		return new ModelAndView("chat/chatting3");
	}
	
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}

	@MessageMapping("/chatroom.create")
	@SendToUser("/queue/chatroom/create")
	public ChatRoom createChatRoom(@Payload ChatRoom chatRoom, SimpMessageHeaderAccessor headerAccessor) {
		String roomId = headerAccessor.getSessionId();
        chatRoom.setId(roomId);
        chatRooms.put(roomId, chatRoom);
        return chatRoom;
    }

    @MessageMapping("/chatroom.join")
    public void joinChatRoom(@Payload ChatRoom chatRoom, SimpMessageHeaderAccessor headerAccessor) {
    	String roomId = chatRoom.getId();
        headerAccessor.getSessionAttributes().put("room_id", roomId);
    }

    @MessageMapping("/chatroom.sendMessage")
    public void sendMessage(@Payload String message, SimpMessageHeaderAccessor headerAccessor) {
    	System.out.println("sendMessage");    	
        String roomId = (String) headerAccessor.getSessionAttributes().get("room_id");
        System.out.println("roomId : "+ roomId);
        System.out.println("message : "+ message);
        // 방 ID를 이용하여 해당 방으로 메시지 브로드캐스트
        simpMessagingTemplate.convertAndSend("/topic/room/" + roomId, message);
        
    }
}

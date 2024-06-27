<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">

const getDustData = function() {

    const request = new XMLHttpRequest();
    
    request.open('GET','dustdata.do', true);
    request.send();

    request.addEventListener('load', function () {
        const data = JSON.parse(this.responseText);

    const items = JSON.parse(data).response.body.items;
    console.log(items[0].stationName);
   
    items.forEach(item => {
    	console.log(item.sidoName);
    	
    	const html = `
        <div style="background-color:beige;">
            <h3>${item.sidoName}, ${item.stationName}</h3>
            <span>${item.coGrade}</span>
            <span>${item.coValue}</span>
            </div>
            `;

        const res = document.getElementById('res');
     
        res.insertAdjacentHTML('afterbegin', html);
    })

    });
};

getDustData(); 
		
		</script>
</head>
<body>
<div id="res">

</div>
</body>
</html>
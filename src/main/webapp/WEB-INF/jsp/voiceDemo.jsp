<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert Form</title>
</head>
<body>

<h2> 게시글 작성 </h2>

<div class="container">
    <form action="/api/v1/chat/sendVoice" method="post" enctype="multipart/form-data"> <!-- 추가 -->

        <input type="file" name="files"> <!-- 추가 -->

        <button type="submit" class="btn btn-primary">작성</button>
    </form>
</div>

</body>
</html>
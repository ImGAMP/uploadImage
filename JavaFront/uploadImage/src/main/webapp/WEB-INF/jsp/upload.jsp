<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Subir Imagen</title>
</head>
<body>
<h1>Subir una imagen</h1>
<form action="/image/upload" method="post" enctype="multipart/form-data">
	<input type="file" name="file" accept="image/*" required>
	<button type="submit">Cargar</button>
</form>
</body>
</html>




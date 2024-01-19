<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Resultado de Procesamiento de Imagen</title>
</head>
<body>
<h1>Resultado de Procesamiento de Imagen</h1>
<img src="data:image/jpeg;base64,${image.imageBase64}" alt="Imagen procesada">

<h2>JSON Resultante</h2>
<pre>${jsonResult}</pre>
</body>
</html>


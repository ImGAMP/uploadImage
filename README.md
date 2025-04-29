# 🖼️ UploadImage – Detección de Objetos con COCO y Aplicación Web en JS

Este proyecto integra un microservicio en Python para detección de objetos utilizando un modelo preentrenado (COCO), junto con una aplicación web en JavaScript para la carga y visualización de resultados.

---

## 🧠 Descripción General

### 🐍 Microservicio en Python
- Utiliza un modelo preentrenado de COCO con OpenCV para detección de objetos.
- Recibe imágenes en formato **PNG** o **JPEG** mediante una API REST.
- Retorna un **JSON** con los objetos detectados y sus posiciones en formato de coordenadas (X, Y, ancho, alto).

### 🌐 Interfaz Web en JavaScript
- Permite al usuario cargar imágenes desde el navegador.
- Envía la imagen al microservicio para su procesamiento.
- Muestra el JSON de respuesta con los objetos detectados.
- **Punto extra**: dibuja sobre la imagen los rectángulos de los objetos detectados.

> 🔧 El intento original de implementación en Java (Primefaces) se encuentra en el repositorio, pero fue reemplazado por una solución web funcional en JS.

---

## ⚙️ Tecnologías Utilizadas

- **Backend Python**: FastAPI, OpenCV, modelo COCO preentrenado
- **Frontend**: JavaScript + HTML + CSS
- **Procesamiento de imágenes**: OpenCV
- **Formato de datos**: JSON

---

## 📂 Estructura del Repositorio

```
uploadImage/
├── python-microservice/             # Microservicio Python (detección de objetos)
│   ├── main.py
│   ├── model/
│   └── requirements.txt
│
├── web-interface/                   # Interfaz Web en JS
│   ├── index.html
│   ├── script.js
│   └── styles.css
│
└── java-webapp/                     # Intento de implementación con Primefaces (no finalizado)
```

---

## 🚀 Instrucciones para ejecución

### 🐍 Microservicio Python
1. Instalar dependencias:
```bash
cd python-microservice
pip install -r requirements.txt
```

2. Ejecutar servidor:
```bash
uvicorn main:app --reload
```

### 🌐 Interfaz Web
1. Abrir `web-interface/index.html` en el navegador.
2. Cargar una imagen y ejecutar detección.
3. Ver el JSON resultante y la imagen con rectángulos dibujados.

---

## 🧪 Ejemplo de respuesta JSON

```json
{
  "detections": [
    {
      "label": "person",
      "confidence": 0.92,
      "box": [130, 85, 200, 300]
    },
    {
      "label": "dog",
      "confidence": 0.88,
      "box": [400, 250, 150, 180]
    }
  ]
}
```

---

## 👨‍💻 Autor

**Gustavo A. Mojica Perdigón**  
Fullstack Developer | Python & JS | Computer Vision & Clean Architecture  
[LinkedIn](https://www.linkedin.com/in/imgamp-it/) | [GitHub](https://github.com/ImGAMP)

---

## 📝 Licencia

MIT License – Uso libre para pruebas técnicas y aprendizaje personal.


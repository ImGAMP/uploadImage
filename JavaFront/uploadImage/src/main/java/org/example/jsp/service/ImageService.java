package org.example.jsp.service;

import org.example.jsp.model.Image;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

@Service
public class ImageService {

    public Image processImage(Image image) {
        try {
            // Convierte la imagen base64 a bytes
            byte[] imageData = Base64Utils.decodeFromString(image.getImageBase64());

            // Lee la imagen desde los bytes
            Mat inputImage = Imgcodecs.imdecode(new MatOfByte(imageData), Imgcodecs.IMREAD_COLOR);

            // Realiza algún procesamiento en la imagen (por ejemplo, cambiar el tamaño, aplicar filtros, etc.)
            Mat processedImage = new Mat();
            Imgproc.resize(inputImage, processedImage, new Size(640, 480)); // Cambia el tamaño de la imagen

            // Convierte la imagen procesada de nuevo a base64
            MatOfByte matOfByte = new MatOfByte();
            Imgcodecs.imencode(".jpg", processedImage, matOfByte);
            byte[] processedImageData = matOfByte.toArray();
            String processedImageBase64 = Base64Utils.encodeToString(processedImageData);

            // Asigna la imagen procesada a la propiedad imageBase64 del objeto Image
            image.setImageBase64(processedImageBase64);

            return image;
        } catch (Exception e) {
            // Manejo de errores
            e.printStackTrace();
            return null;
        }
    }
}

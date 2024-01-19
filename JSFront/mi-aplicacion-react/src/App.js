import React, { useState } from 'react';
import './App.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.css';
import 'primeicons/primeicons.css';
import { FileUpload } from 'primereact/fileupload';
import { Button } from 'primereact/button';

function App() {
    const [image, setImage] = useState(null);
    const [processedImage, setProcessedImage] = useState(null);
    const [showProcessedImage, setShowProcessedImage] = useState(false);

    const onUpload = (e) => {
        setShowProcessedImage(false); // Ocultar la imagen procesada anterior si hay una
        const reader = new FileReader();
        reader.onload = (e) => setImage(e.target.result);
        reader.readAsDataURL(e.files[0]);
    };

    const sendImage = async () => {
        try {
            const base64Image = image.split(',')[1] || image;
            const response = await fetch('http://localhost:5000/process_image', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ image: base64Image })
            });
            const data = await response.json();

            if (data && data.objects && data.processed_image) {
                setProcessedImage(data.processed_image);

                // Mostrar la respuesta JSON en la consola
                console.log("Respuesta JSON:", data.objects);
            } else {
                console.error('La respuesta del servidor no contiene las propiedades esperadas:', data);
            }
        } catch (error) {
            console.error('Error al enviar la imagen:', error);
        }
    };

    const handleShowProcessedImage = () => {
        setShowProcessedImage(true); // Mostrar la imagen procesada
    };

    return (
        <div>
            <FileUpload name="demo[]" customUpload uploadHandler={onUpload} accept="image/*" />
            {image && <img alt="Preview" src={image} style={{ maxWidth: '500px' }} />}

            <Button label="Enviar Imagen" onClick={sendImage} className="p-mt-2" />

            {showProcessedImage && processedImage && (
                <div>
                    <h3>Imagen Procesada</h3>
                    <img alt="Processed" src={`data:image/png;base64,${processedImage}`} style={{ maxWidth: '500px' }} />
                </div>
            )}

            {processedImage && (
                <Button label="Mostrar Imagen Procesada" onClick={handleShowProcessedImage} className="p-mt-2" />
            )}
        </div>
    );
}

export default App;




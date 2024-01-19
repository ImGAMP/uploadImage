from flask import Flask, request, jsonify
from flask_cors import CORS
import torch
import cv2
import base64
import matplotlib.pyplot as plt
import numpy as np
import io

app = Flask(__name__)
CORS(app)

def detect_objects(image):
   
    model = torch.hub.load('ultralytics/yolov5', 'yolov5s')
    results = model(image)
    
 
    fig, ax = plt.subplots(figsize=(16, 12))
    ax.imshow(results.render()[0])
   
    buf = io.BytesIO()
    plt.savefig(buf, format='png')
    buf.seek(0)
   
    encoded_img = base64.b64encode(buf.read()).decode('utf-8')


    boxes = results.xyxy[0].cpu().numpy().tolist()
    objects = []
    for box in boxes:
        obj_info = {
            'label': int(box[5]),  
            'confidence': float(box[4]),  
            'coordinates': {
                'x_min': float(box[0]),
                'y_min': float(box[1]),
                'x_max': float(box[2]),
                'y_max': float(box[3]),
            }
        }
        objects.append(obj_info)

    json_results = {
        'objects': objects,
        'processed_image': encoded_img
    }

    return json_results

@app.route('/process_image', methods=['POST'])
def process_image():
    try:
        image_base64 = request.json['image']
        print("Imagen Base64 recibida:", image_base64[:100])  

        image_decoded = np.frombuffer(base64.b64decode(image_base64), dtype=np.uint8)
        image = cv2.imdecode(image_decoded, cv2.IMREAD_COLOR)

        if image is None:
            raise ValueError("La imagen no se pudo decodificar.")

        response_json = detect_objects(image)
        return response_json
    except Exception as e:
        print("Error al procesar la imagen:", e)
        return jsonify({"error": str(e)})

if __name__ == '__main__':
    app.run(debug=True)


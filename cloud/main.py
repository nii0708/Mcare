from flask import Flask, request, jsonify
from flask_restful import Resource, Api, reqparse
import pandas as pd
import ast
import numpy as np
from keras.models import model_from_json
import tensorflow as tf
import os
os.environ["CUDA_VISIBLE_DEVICES"] = "-1"

app = Flask(__name__)
api = Api(app)

def detection(text1,text2,text3):
    embedding_dim = 100
    max_length = 16
    trunc_type='post'
    padding_type='pre'
    oov_tok = "<OOV>"
    stopword = [ "a", "about", "am", "an", "and", "any", "are", "as", "at", "be", "because", "been", "before", "between", "both", "but", "by", "could", "did", "do", "does", "doing", "down", "during", "each", "few", "for", "from", "further", "had", "has", "have", "having", "he", "he'd", "he'll", "he's", "her", "here", "here's", "hers", "herself", "him", "himself", "his", "how", "how's", "into", "is", "it", "it's", "its", "itself", "let's", "me", "more", "most", "nor", "of", "on", "once", "only", "or", "other", "ought", "our", "ours", "ourselves", "out", "own", "same", "she", "she'd", "she'll", "she's", "should", "so", "some", "such", "than", "that", "that's", "the", "their", "theirs", "them", "themselves", "then", "there", "there's", "these", "they", "they'd", "they'll", "they're", "they've", "this", "those", "through", "to", "too", "under", "until", "up",  "was", "we", "we'd", "we'll", "we're", "we've", "were", "what", "what's", "when", "when's", "where", "where's", "which", "while", "who", "who's", "whom", "why", "why's", "with" ] 

    with open('model.json','r') as json_model:
        load_model = json_model.read()

    with open('token.json','r') as json_model:
        load_tokenizer = json_model.read()

    model=model_from_json(load_model)
    model.load_weights('model.h5')

    sentences = text1 +' '+ text2 +' '+ text3

    for word in stopword:
        token = " " + word + " "
        sentences = sentences.replace(token, " ")
        sentences = sentences.replace("  ", " ")
        sentences = sentences.replace("'", " ")

    sentences=[sentences]

    tokenizer = tf.keras.preprocessing.text.tokenizer_from_json(load_tokenizer)
    tokenizer.fit_on_texts(sentences) 

    word_index = tokenizer.word_index
    vocab_size=len(word_index)    

    sequences = tokenizer.texts_to_sequences(sentences)  
    padded = tf.keras.preprocessing.sequence.pad_sequences(sequences, maxlen=max_length, padding = padding_type, truncating=trunc_type)

    prediction = np.round(model.predict(padded)[0][0])

    message = {'predict': int(prediction)}
    
    return message



class detect(Resource):
    
    def post(self):
        text  = request.json
        text1 = text['text1']
        text2 = text['text2']
        text3 = text['text3']
        
        return jsonify(detection(text1,text2,text3))
        
api.add_resource(detect, '/detect')

if __name__ == '__main__':
    app.run(debug=True)  # run our Flask app
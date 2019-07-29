#coding: utf-8

from flask import Flask, request, jsonify
from flask_restful import Resource, Api
import pandas as pd
import numpy as np
from decimal import Decimal

app = Flask(__name__)

dataset = pd.read_csv('dataset/reduce.csv')

def calculate(age):
    index = int(age) + 2
    sum = np.sum(dataset.values[:, index])
    n = Decimal(Decimal(1) / Decimal(sum))
    np.amax(dataset.values[:, index])
    i = np.where(dataset.values[:, 2] == np.amax(dataset.values[:, 2]))[0]
    return dataset.values[i, 0][0], float(float(dataset.values[i, index][0]) * float(n))



@app.route("/mortality/<age>", methods=['GET'])
def get(age):
        cause, probability = calculate(age)
        result = {
            'cause': str(cause),
            "probability": float(probability)
        }
        return jsonify(result)

if __name__ == '__main__':
    app.run(debug=False,host='0.0.0.0')

# -*- coding: utf-8 -*-
"""
Created on Sat Jul 25 14:49:19 2020

@author: Pratheeksha
"""

import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
# Importing the dataset
dataset = pd.read_csv('C://Users//Pratheeksha//Downloads//HRdata.csv')
X = dataset.iloc[:, :8].values
y = dataset.iloc[:, 9].values
from sklearn.utils import shuffle
X, y = shuffle(X, y)
from sklearn.preprocessing import LabelEncoder, OneHotEncoder
labelencoder_X_1 = LabelEncoder()
X[:, 6] = labelencoder_X_1.fit_transform(X[:, 6])
labelencoder_X_2 = LabelEncoder()
X[:, 7] = labelencoder_X_2.fit_transform(X[:, 7])
onehotencoder = OneHotEncoder(categorical_features = [1])
X = onehotencoder.fit_transform(X).toarray()
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 0)
from sklearn.preprocessing import StandardScaler
sc = StandardScaler()
X_train_sc = sc.fit_transform(X_train)
X_test_sc = sc.transform(X_test)
from sklearn.model_selection import KFold 
kf = KFold(n_splits=5,  random_state=None) 
for train_index, test_index in kf.split(X):
      print("Train:", train_index, "Validation:",test_index)
      X_train, X_test = X[train_index], X[test_index] 
      y_train, y_test = y[train_index], y[test_index]
from keras.models import Sequential
from keras.layers import Dense, Dropout
# Initialising the ANN
classifier = Sequential()
# Adding the input layer and the first hidden layer
classifier.add(Dense(units = 16, kernel_initializer = 'uniform', activation = 'relu', input_dim = 9))
# Adding the second hidden layer
classifier.add(Dense(units = 8, kernel_initializer = 'uniform', activation = 'relu'))
classifier.add(Dropout(0.2))
classifier.add(Dense(units = 6, kernel_initializer = 'uniform', activation = 'relu'))
classifier.add(Dropout(0.2))
# Adding the output layer
classifier.add(Dense(units = 1, kernel_initializer = 'uniform', activation = 'sigmoid'))
# Compiling the ANN
classifier.compile(optimizer = 'adam', loss = 'binary_crossentropy', metrics = ['accuracy'])
# Fitting the ANN to the Training set
classifier.fit(X_train, y_train, batch_size = 10, epochs = 80)
testset = pd.read_csv('C://Users//Pratheeksha//Downloads//test.csv')
A = testset.iloc[:, :8].values



from sklearn.preprocessing import LabelEncoder, OneHotEncoder
labelencoder_A_1 = LabelEncoder()
A[:, 6] = labelencoder_A_1.fit_transform(A[:, 6])
labelencoder_A_2 = LabelEncoder()
A[:, 7] = labelencoder_A_2.fit_transform(A[:, 7])
onehotencoder = OneHotEncoder(categorical_features = [1])
A = onehotencoder.fit_transform(A).toarray()
b = classifier.predict_classes(A)
b
classifier.save_weights("hrmodel.hdf5")
model_json = classifier.to_json()
tfile =open("hrmodel_json","w")
tfile.write(model_json)
tfile.close()


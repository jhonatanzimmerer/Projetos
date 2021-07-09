import pandas as pd
import numpy as np
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split

dado = pd.read_csv("train.csv")

print(dado.head())
dado = dado.drop(['Name'], axis = 1)

dado = dado.reset_index(['Passengerid'])
dado = dado.rename(coluns = {'survived': 'target'}, inplace = False)

dado.discribe()
dado.discribe(inlcude=['O'])

dado['Sex_F'] = np.where(dado['Sex'] == 'female', 1, 0)

dado['Pclass_1'] = np.where(dado['Pclass'] == 1, 1, 0)
dado['Pclass_2'] = np.where(dado['Pclass'] == 2, 1, 0)
dado['Pclass_3'] = np.where(dado['Pclass'] == 3, 1, 0)

dado = dado.drpo(['Pclass','Sex'], axis = 1)

print(dado.isnull().sum())

dado.fillna(0, inplace = True)



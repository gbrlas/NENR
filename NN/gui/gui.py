from tkinter import *
import numpy as np

from neural_network import layers
from neural_network.NN import forward_pass, train
from input_processing import  processor

def clear_canvas(event):
    canvas.delete('all')

def train_nn():
    X, y = processor.get_data()

    config = {}
    config['max_epochs'] = 20000
    config['learning_rate'] = 0.0015
    config['batch_size'] = 100

    global net

    net = []
    net += [layers.FC(X, 40, "fc0")]
    net += [layers.FC(net[-1], 200, "fc1")]
    net += [layers.FC(net[-1], 100, "fc2")]
    net += [layers.FC(net[-1], 100, "fc3")]
    net += [layers.FC(net[-1], 5, "fc4")]
    net += [layers.Sigmoid(net[-1], "s4")]

    loss = layers.MSE()

    train(X, y, net, loss, config)

def draw(event):
    x, y = event.x, event.y
    points.append([x, y])
    canvas.create_oval(x - 1, y - 1, x + 1, y + 1, fill='black')


def save(event):
    global drawing_index

    symbol_index = int((drawing_index - 1) / samples_per_class)
    symbol = processor.get_symbol(symbol_index)
    remaining = int((symbol_index + 1) * samples_per_class - drawing_index)

    if remaining > 0:
        label.config(text=processor.get_symbol(symbol_index) + ' remaining: ' + str(remaining))
    else:
        label.config(text=processor.get_symbol(symbol_index + 1) + ' remaining: ' + str(20))

    is_saved = processor.store_data(np.array(points), symbol, features)

    if drawing_index == train_data_len:
        fit()

    if is_saved:
        drawing_index += 1

    points.clear()

def fit():
    canvas.unbind('<ButtonRelease-1>')
    print('Fitting...')

    train_nn()

    global isFitted
    isFitted = True


def train_button():
    processor.delete_data()
    label.config(text='Start training alpha!')
    label.pack(side=TOP, fill=X, expand=True)
    canvas.bind('<B1-Motion>', draw)
    canvas.bind('<ButtonRelease-1>', save)
    canvas.bind('<Button-1>', clear_canvas)

def predict_button():
    global isFitted
    if not isFitted:
        train_nn()
        isFitted = True

    canvas.bind('<B1-Motion>', draw)
    canvas.bind('<ButtonRelease-1>', get_solution)
    canvas.bind('<Button-1>', clear_canvas)
    label.config(text='Draw a symbol!')
    label.pack()


def get_solution(event):
    gesture = processor.get_processed(np.array(points), features)
    print(gesture)

    global net

    y = forward_pass(net, gesture)
    print('\nPredicting...')
    print('Drawn simbol is ' + processor.get_symbol(np.argmax(y)) + '.\t' + str(y))
    points.clear()


features = 10
train_data_len = 100
classes = 5
samples_per_class = 20


window = Tk()
window.title("NN symbol training and prediction")

label = Label(window)
label.pack(side=TOP)

canvas = Canvas(window, height=500, width=500);
canvas.pack()

button_train = Button(window, text='Train NN from scratch', command=train_button);
button_train.pack(side=LEFT, fill=X, expand=True)
button_predict = Button(window, text='Predict symbol', command=predict_button);
button_predict.pack(side=RIGHT, fill=X, expand=True)

drawing_index = 1
isFitted = False
points = []
net = []

window.mainloop()

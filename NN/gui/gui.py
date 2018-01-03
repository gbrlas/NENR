from tkinter import *
import numpy as np

from neural_network import layers
from neural_network.NN import forward_pass, train
from input_processing import data_transformation, data_processing

def reset_canvas(event):
    canvas.delete('all')

def train_nn():
    canvas.unbind('<ButtonRelease-1>')
    print('Training NN...')

    X, y = data_processing.load_data()

    config = {}
    config['max_epochs'] = 30000
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

    global isFitted
    isFitted = True

def draw(event):
    x, y = event.x, event.y
    points.append([x, y])
    canvas.create_oval(x - 1, y - 1, x + 1, y + 1, fill='black')


def save_points(event):
    global drawing_index

    symbol_index = int((drawing_index - 1) / samples_per_class)
    symbol = data_processing.get_symbol(symbol_index)
    remaining = int((symbol_index + 1) * samples_per_class - drawing_index)

    if remaining > 0:
        label.config(text=data_processing.get_symbol(symbol_index) + ' remaining: ' + str(remaining))
    else:
        label.config(text=data_processing.get_symbol(symbol_index + 1) + ' remaining: ' + str(20))

    is_saved = data_processing.save_data(np.array(points), symbol, features)

    if drawing_index == train_data_len:
        train_nn()

    if is_saved:
        drawing_index += 1

    points.clear()

def train_button():
    data_processing.delete_data()

    label.config(text='Start training alpha!')
    label.pack(side=TOP, fill=X, expand=True)

    canvas.bind('<B1-Motion>', draw)
    canvas.bind('<ButtonRelease-1>', save_points)
    canvas.bind('<Button-1>', reset_canvas)

def predict_button():
    global isFitted

    if not isFitted:
        train_nn()
        isFitted = True

    label.config(text='Draw a symbol!')
    label.pack(side=TOP, fill=X, expand=True)

    canvas.bind('<B1-Motion>', draw)
    canvas.bind('<ButtonRelease-1>', get_solution)
    canvas.bind('<Button-1>', reset_canvas)



def get_solution(event):
    drawn_symbol = data_transformation.process_points(np.array(points), features)

    global net

    y = forward_pass(net, drawn_symbol)
    print('Drawn symbol is ' + data_processing.get_symbol(np.argmax(y)) + '.\t' + str(y))

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
button_predict = Button(window, text='Predict symbols', command=predict_button);
button_predict.pack(side=RIGHT, fill=X, expand=True)

drawing_index = 1
isFitted = False
points = []
net = []

window.mainloop()

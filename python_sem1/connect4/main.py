#7 lg 6 inaltime
#from texttable import Texttable
#from tools import GameException
#from circle import Circle
#from board import Board
#from game import Game
from UI import UI
from GUI import GUI
#from random import choice,randint
#g=Game()
#print(randint(3,4))
ok=True
while ok:
    print('0-Quit')
    print('1-Console')
    print('2-GUI')
    n=input().strip()
    if n=='0':
        print('Bye')
        ok=False
    elif n=='1':
        ui=UI()
        ui.start()
    elif n=='2':
        gui=GUI()
        gui.start()
    else:
        print('Wrong input,try again')

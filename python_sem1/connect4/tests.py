import unittest
from board import Board
from circle import Circle
from tools import checkMoveInList,getBestMove,isInt
class testClass(unittest.TestCase):
    def setUp(self):
        self.board=Board()
    def tearDown(self):
        del self.board
    def test_minimax(self):
        for i in [0,2,4]:
            self.board.move(Circle(5,i),'X')
            self.board.move(Circle(4,i),'X')
            self.board.move(Circle(3,i),'X')
            self.board.move(Circle(2,i),'0')
            self.board.move(Circle(1,i),'X')

            self.board.move(Circle(5,i+1),'0')
            self.board.move(Circle(4,i+1),'0')
            self.board.move(Circle(3,i+1),'0')
            self.board.move(Circle(2,i+1),'X')
            self.board.move(Circle(1,i+1),'0')

        self.board.move(Circle(5,6),'X')
        self.board.move(Circle(4,6),'X')
        self.board.move(Circle(3,6),'X')
        self.board.move(Circle(2,6),'0')
        self.board.move(Circle(1,6),'X')

        #self.board.move(Circle(1,5),' ')
        self.board.move(Circle(1,3),'X')
        self.board.move(Circle(3,3),'X')

        #self.board.move(Circle(0,6),'0')
        #self.board.move(Circle(0,0),'X')
        #self.board.move(Circle(0,1),'0')
        #self.board.move(Circle(0,2),'X')
        self.board.move(Circle(0,3),'0')
        self.board.move(Circle(0,4),'0')
        self.board.move(Circle(2,3),'0')
        #self.board.move(Circle(1,4),'0')

        move=getBestMove(self.board,'_aiPlayer')
        print(self.board)
        print(move[0].row,move[0].col)
        self.board.move(move[0],'0')
        self.board.move(Circle(0,5),'X')
        move=getBestMove(self.board,'_aiPlayer')
        print(self.board)
        print(move[0].row,move[0].col)
        self.board.move(move[0],'0')
        self.assertEqual(self.board.win(),True)
        self.assertEqual(self.board.tie(),False)
        self.assertEqual(self.board.winWHO(),16)

    def test_isint(self):
        self.assertEqual(isInt('5'),True)
        self.assertEqual(isInt('cinci'),False)
        self.assertEqual(isInt('5a'),False)

    def test_playableCircles(self):
        self.assertEqual(len(self.board.playableCircles()),7)
    def test_winwho(self):
        self.board.move(Circle(5,3),'X')
        self.board.move(Circle(4,3),'X')
        self.board.move(Circle(3,3),'X')
        self.board.move(Circle(2,3),'X')
        self.assertEqual(self.board.winWHO(),1)
if __name__ == "__main__":
    unittest.main()

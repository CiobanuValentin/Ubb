#include "sesiuneOOP.h"
#include <QtWidgets/QApplication>
#include "repository.h"
#include "searchWindow.h"
#include <assert.h>

void testAddQuestion()
{
	repository repo(0);
	int nr = repo.getQSize();
	std::string text = "text";
	std::string name = "name";
	question que(3, text, name);
	repo.addQuestion(que);
	assert(repo.getQSize() == nr + 1);
}
void testGetAnswers()
{
	repository repo(0);
	assert(repo.getAnswersForQuestion(1).size() == 1);
}
int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	std::ifstream fileIn("users.txt");
	std::vector<sesiuneOOP*> windows;
	std::string name;
	testAddQuestion();
	repository repo;
	searchWindow* sw=new searchWindow(repo);
	sw->show();
	repo.addObserver(sw);
	while (fileIn >> name)
	{
		sesiuneOOP* w=new sesiuneOOP(name,repo);
		windows.push_back(w);
		repo.addObserver(w);
		w->show();
	}
	return a.exec();
}

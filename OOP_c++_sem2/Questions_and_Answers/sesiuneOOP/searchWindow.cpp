#include "searchWindow.h"
bool cmp2(answer a, answer b)
{
	return a.getVotes() > b.getVotes();
}
searchWindow::searchWindow(repository& _repo,QWidget *parent)
	: QWidget(parent),repo(_repo)
{
	ui.setupUi(this);
	connect(ui.lineEdit, SIGNAL(textChanged(const QString &)), this, SLOT(handleInput(const QString &)));
}

searchWindow::~searchWindow()
{
}

void searchWindow::afisare(std::string s)
{
	ui.listWidget->clear();
	std::vector<question> questions = repo.getQuestions();
	char c[256],v[256];
	strcpy(c, s.c_str());
	for (auto i : questions)
	{
		s = i.getText();
		bool ok = true;
		strcpy(v, s.c_str());
		for (int t = 0;t < strlen(c);t++)
			if (c[t] != v[t])
			{
				ok = false;break;
			}
		if (ok)
		{
			std::string print = std::to_string(i.getId()) + ", " + i.getName() + ", " + i.getText();
			new QListWidgetItem(print.c_str(), ui.listWidget);
			//mai tb adaugate answers
			std::vector<answer> v=repo.getAnswersForQuestion(i.getId());
			sort(v.begin(), v.end(), cmp2);
			int maxlg = v.size();
			if (maxlg > 3) maxlg = 3;
			for (int ii = 0;ii < maxlg;ii++)
			{
				std::string ans = "	" + std::to_string(v[ii].getMyId()) + ", " + v[ii].getName() + ", " + v[ii].getText() + ", " + std::to_string(v[ii].getVotes());
				new QListWidgetItem(ans.c_str(), ui.listWidget);
			}

		}
	}
}

void searchWindow::handleInput(const QString & q)
{
	afisare(q.toStdString());
}
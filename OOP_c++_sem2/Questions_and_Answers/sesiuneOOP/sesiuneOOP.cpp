#include "sesiuneOOP.h"
bool cmp(question  a, question b)
{
	return a.getAnswers() > b.getAnswers();
}


sesiuneOOP::sesiuneOOP(std::string _name,repository& _repo,QWidget *parent)
	: QMainWindow(parent),name(_name),repo(_repo)
{
	ui.setupUi(this);
	this->setWindowTitle(name.c_str());
	afisare();
	connect(ui.addButton, SIGNAL(clicked()), this, SLOT(handleAdd()));
	connect(ui.answerButton, SIGNAL(clicked()), this, SLOT(handleAnswer()));
	connect(ui.listWidget, SIGNAL(itemClicked(QListWidgetItem*)),
		this, SLOT(handleClick(QListWidgetItem*)));

	connect(ui.listWidgetAnswers, SIGNAL(itemClicked(QListWidgetItem*)),
		this, SLOT(handleClickAnswer(QListWidgetItem*)));


	connect(ui.spinBox, SIGNAL(valueChanged(int)), this, SLOT(voted(int)));
}
void sesiuneOOP::voted(int x)
{
	//QMessageBox q;
	//q.critical(0, "Error", std::to_string(x).c_str());
	/*int nr = ui.listWidget->currentIndex().row();
	std::vector<question> questions = repo.getQuestions();
	//aici sortare;
	sort(questions.begin(), questions.end(), cmp);
	question selected= questions[nr];
	std::vector<answer> answers = repo.getAnswers();
	std::vector<answer> ans;
	for (auto i : answers)
	{
		if (i.getQuestionId() == selected.getId())
		{
			ans.push_back(i);
		}
	}
	int nr2 = ui.listWidgetAnswers->currentIndex().row();
	*/

	//repo.incVotes(ans[nr2].getMyId(),x);
	repo.incVotes(selectedAns, x);
	repo.notify();
}
void sesiuneOOP::handleClickAnswer(QListWidgetItem* q)
{
	if (q->backgroundColor() == Qt::yellow)
		ui.spinBox->setDisabled(true);
	else {
		ui.spinBox->setEnabled(true);
		int nr = ui.listWidgetAnswers->currentIndex().row();
		/*std::vector<answer> answers = repo.getAnswers();
		std::vector<answer> ans;
		for (auto i : answers)
		{
			if (i.getQuestionId() == selected.getId())
			{
			}
		}*/
		std::string text2 = q->text().toStdString();
		auto it2 = text2.begin();
		while ((*it2) != ',')
			it2++;
		text2.erase(it2, text2.end());
		this->selectedAns = stoi(text2);
		std::string text = q->text().toStdString();
		auto it = text.end();
		it--;
		while ((*it) != ',')
			it--;
		it++;it++;
		text.erase(text.begin(), it);

		ui.spinBox->setValue(stoi(text));
	}
}
void sesiuneOOP::afisare()
{
	ui.listWidget->clear();
	std::vector<question> questions = repo.getQuestions();
	//aici sortare;
	sort(questions.begin(), questions.end(), cmp);
	for (auto i : questions)
	{
		std::string print = std::to_string(i.getId()) + ", " + i.getName() +", "+ i.getText();
		new QListWidgetItem(print.c_str(), ui.listWidget);
	}
}


void  sesiuneOOP::handleAnswer()
{
	try {
		std::string input = ui.answerInput->text().toStdString();
		if (input == "")
		{
			//QMessageBox q;
			//q.critical(0, "Error", "Please fill everything");
			throw std::exception();
		}
		else
		{
			int nr = repo.getASize();
			nr++;nr *= 11;
			int number = this->ui.listWidget->currentIndex().row();
			std::vector<question> questions = repo.getQuestions();
			//aici sortare;
			sort(questions.begin(), questions.end(), cmp);
			int questionID = questions[number].getId();
			repo.addAnswer(answer(nr, questionID, this->name, input));

		}
	}
	catch (std::exception e) {
		QMessageBox q;
		q.critical(0, "Error", "Please fill everything");
	}

}
void sesiuneOOP::handleAdd()
{
	std::string input = ui.lineEdit->text().toStdString();
	try {
		if (input == "")
		{
			throw std::exception();
			//QMessageBox q;
			//q.critical(0, "Error", "Please fill everything");
		}
		else
		{
			int nr = repo.getQSize();
			nr++;
			repo.addQuestion(question(nr, input, this->name));
			this->afisare();
		}
	}
	catch (std::exception e) {
		QMessageBox q;
		q.critical(0, "Error", "Please fill everything");
	}
}
void sesiuneOOP::handleClick(QListWidgetItem* q )
{
	ui.listWidgetAnswers->clear();
	int nr = ui.listWidget->currentIndex().row();
	std::vector<question> questions = repo.getQuestions();
	//aici sortare;
	sort(questions.begin(), questions.end(), cmp);

	question selected = questions[nr];
	selectedQuestion = selected.getId();
	std::vector<answer> answers = repo.getAnswers();
	for (auto i : answers)
	{
		if (i.getQuestionId() == selected.getId())
		{
			std::string print = std::to_string(i.getMyId()) + ", " + i.getName() + ", " + i.getText() + ", " + std::to_string(i.getVotes());
			QListWidgetItem* pp = new QListWidgetItem(print.c_str(), ui.listWidgetAnswers);
			if(this->name==i.getName())
				pp->setBackgroundColor(Qt::yellow);
		}
	}
}
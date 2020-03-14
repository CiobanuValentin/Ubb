#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_sesiuneOOP.h"
#include "repository.h"
#include <qmessagebox.h>
#include "observer.h"
class sesiuneOOP : public QMainWindow,public observer
{
	Q_OBJECT

public:
	sesiuneOOP(std::string _name,repository& _repo,QWidget *parent = Q_NULLPTR);

private:
	int selectedAns;
	int selectedQuestion;
	std::string name;
	repository& repo;
	Ui::sesiuneOOPClass ui;
	void afisare();
	void afisare2()
	{
		this->ui.listWidgetAnswers->clear();
		std::vector<answer> answers = repo.getAnswers();
		for (auto i : answers)
		{
			if (i.getQuestionId() == repo.getIdQofAnswer(selectedAns))
			{
				std::string print = std::to_string(i.getMyId()) + ", " + i.getName() + ", " + i.getText() + ", " + std::to_string(i.getVotes());
				QListWidgetItem* pp = new QListWidgetItem(print.c_str(), ui.listWidgetAnswers);
				if (this->name == i.getName())
					pp->setBackgroundColor(Qt::yellow);
			}
		}
	}
	void update() { afisare();afisare2(); }
private slots:
	void handleAdd();
	void handleAnswer();
	void handleClick(QListWidgetItem* q);
	void handleClickAnswer(QListWidgetItem* q);
	void voted(int x);
};

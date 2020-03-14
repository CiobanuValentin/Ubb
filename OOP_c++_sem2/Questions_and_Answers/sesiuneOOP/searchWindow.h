#pragma once

#include <QWidget>
#include "ui_searchWindow.h"
#include "repository.h"
#include "observer.h"
class searchWindow : public QWidget,public observer
{
	Q_OBJECT

public:
	searchWindow(repository& _repo,QWidget *parent = Q_NULLPTR);
	~searchWindow();

private:
	repository& repo;
	Ui::searchWindow ui;
	void afisare(std::string s);
	void update() { std::string s="";afisare(s); }
private slots:
	void handleInput(const QString & q);
};

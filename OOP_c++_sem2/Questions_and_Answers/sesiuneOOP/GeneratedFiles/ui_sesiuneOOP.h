/********************************************************************************
** Form generated from reading UI file 'sesiuneOOP.ui'
**
** Created by: Qt User Interface Compiler version 5.12.3
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SESIUNEOOP_H
#define UI_SESIUNEOOP_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpinBox>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_sesiuneOOPClass
{
public:
    QWidget *centralWidget;
    QGridLayout *gridLayout;
    QListWidget *listWidget;
    QPushButton *addButton;
    QLineEdit *lineEdit;
    QPushButton *answerButton;
    QLineEdit *answerInput;
    QSpinBox *spinBox;
    QListWidget *listWidgetAnswers;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *sesiuneOOPClass)
    {
        if (sesiuneOOPClass->objectName().isEmpty())
            sesiuneOOPClass->setObjectName(QString::fromUtf8("sesiuneOOPClass"));
        sesiuneOOPClass->resize(600, 533);
        centralWidget = new QWidget(sesiuneOOPClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        gridLayout = new QGridLayout(centralWidget);
        gridLayout->setSpacing(6);
        gridLayout->setContentsMargins(11, 11, 11, 11);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        listWidget = new QListWidget(centralWidget);
        listWidget->setObjectName(QString::fromUtf8("listWidget"));

        gridLayout->addWidget(listWidget, 0, 0, 1, 2);

        addButton = new QPushButton(centralWidget);
        addButton->setObjectName(QString::fromUtf8("addButton"));

        gridLayout->addWidget(addButton, 1, 0, 1, 1);

        lineEdit = new QLineEdit(centralWidget);
        lineEdit->setObjectName(QString::fromUtf8("lineEdit"));

        gridLayout->addWidget(lineEdit, 1, 1, 1, 1);

        answerButton = new QPushButton(centralWidget);
        answerButton->setObjectName(QString::fromUtf8("answerButton"));

        gridLayout->addWidget(answerButton, 2, 0, 1, 1);

        answerInput = new QLineEdit(centralWidget);
        answerInput->setObjectName(QString::fromUtf8("answerInput"));

        gridLayout->addWidget(answerInput, 2, 1, 1, 1);

        spinBox = new QSpinBox(centralWidget);
        spinBox->setObjectName(QString::fromUtf8("spinBox"));

        gridLayout->addWidget(spinBox, 3, 0, 1, 1);

        listWidgetAnswers = new QListWidget(centralWidget);
        listWidgetAnswers->setObjectName(QString::fromUtf8("listWidgetAnswers"));

        gridLayout->addWidget(listWidgetAnswers, 3, 1, 1, 1);

        sesiuneOOPClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(sesiuneOOPClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 600, 21));
        sesiuneOOPClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(sesiuneOOPClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        sesiuneOOPClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(sesiuneOOPClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        sesiuneOOPClass->setStatusBar(statusBar);

        retranslateUi(sesiuneOOPClass);

        QMetaObject::connectSlotsByName(sesiuneOOPClass);
    } // setupUi

    void retranslateUi(QMainWindow *sesiuneOOPClass)
    {
        sesiuneOOPClass->setWindowTitle(QApplication::translate("sesiuneOOPClass", "sesiuneOOP", nullptr));
        addButton->setText(QApplication::translate("sesiuneOOPClass", "Add", nullptr));
        answerButton->setText(QApplication::translate("sesiuneOOPClass", "Answer", nullptr));
    } // retranslateUi

};

namespace Ui {
    class sesiuneOOPClass: public Ui_sesiuneOOPClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SESIUNEOOP_H

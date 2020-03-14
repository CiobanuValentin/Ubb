/********************************************************************************
** Form generated from reading UI file 'searchWindow.ui'
**
** Created by: Qt User Interface Compiler version 5.12.3
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SEARCHWINDOW_H
#define UI_SEARCHWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_searchWindow
{
public:
    QGridLayout *gridLayout;
    QLineEdit *lineEdit;
    QListWidget *listWidget;

    void setupUi(QWidget *searchWindow)
    {
        if (searchWindow->objectName().isEmpty())
            searchWindow->setObjectName(QString::fromUtf8("searchWindow"));
        searchWindow->resize(400, 300);
        gridLayout = new QGridLayout(searchWindow);
        gridLayout->setSpacing(6);
        gridLayout->setContentsMargins(11, 11, 11, 11);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        lineEdit = new QLineEdit(searchWindow);
        lineEdit->setObjectName(QString::fromUtf8("lineEdit"));

        gridLayout->addWidget(lineEdit, 0, 0, 1, 1);

        listWidget = new QListWidget(searchWindow);
        listWidget->setObjectName(QString::fromUtf8("listWidget"));

        gridLayout->addWidget(listWidget, 1, 0, 1, 1);


        retranslateUi(searchWindow);

        QMetaObject::connectSlotsByName(searchWindow);
    } // setupUi

    void retranslateUi(QWidget *searchWindow)
    {
        searchWindow->setWindowTitle(QApplication::translate("searchWindow", "searchWindow", nullptr));
    } // retranslateUi

};

namespace Ui {
    class searchWindow: public Ui_searchWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SEARCHWINDOW_H

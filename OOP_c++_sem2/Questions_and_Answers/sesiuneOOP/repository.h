#pragma once
#include "answer.h"
#include "question.h"
#include <fstream>
#include <vector>
#include "subject.h"
class repository:public subject
{
private:
	std::vector<question> questions;
	std::vector<answer> answers;
	int save;
public:
	repository(int x=1);
	~repository();
	std::vector<question> getQuestions() { return this->questions; }
	int getQSize() { return questions.size(); }
	int getIdQofAnswer(int id){
		for (auto i : answers)
		{
			if (i.getMyId() == id) return i.getQuestionId();
	}
	}
	int getASize() { return answers.size(); }
	/*
	input : a question q
	output: no output
	it adds the question q to the vector questions;
	*/
	void addQuestion(question q) { questions.push_back(q);this->notify(); }
	/*
	input: an int id;
	output: a vector<answer> containing all the answers for the question with the given
	id
	it builds a vector with all the answers for a specific question,more exactly
	the question with the given id
	*/
	std::vector<answer> getAnswersForQuestion(int id)
	{
		std::vector<answer> v;
		for (auto i : answers)
		{
			if (i.getQuestionId() == id)
				v.push_back(i);
		}
		return v;
	}
	void addAnswer(answer a) { answers.push_back(a);
		for (auto& i : questions)
		{
			if (i.getId() == a.getQuestionId()) i.incAnswers();break;
		}
		this->notify();
	}
	std::vector<answer> getAnswers() { return this->answers; }
	void incVotes(int x,int y){
		for (auto& i : answers)
		{
			if (i.getMyId() == x)
			{
				i.incVotes(y);
				break;
			}
		}
		this->notify();
	}
};


#pragma once
#include <string>
class question
{
private:
	std::string text, name;
	int id;
	int _answers;
public:
	question(int _id,std::string _text,std::string _name);
	~question();
	std::string getText() { return text; }
	std::string getName() { return name; }
	int getId() { return id; }
	void incAnswers() { _answers++; }
	int getAnswers() { return _answers; }
};


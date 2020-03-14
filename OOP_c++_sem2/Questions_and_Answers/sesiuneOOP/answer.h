#pragma once
#include <string>
class answer
{
private:
	int myId, qId,votes;
	std::string name, text;
public:
	answer(int _myId,int _qId,std::string _name,std::string _text,int _votes=0);
	~answer();
	int getMyId() { return myId; }
	int getQuestionId() { return qId; }
	int getVotes() { return votes; }
	std::string getName() { return name; }
	std::string getText() { return text; }
	void incVotes(int x) { votes = x; }
};


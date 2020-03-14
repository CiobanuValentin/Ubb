#include "question.h"



question::question(int _id, std::string _text, std::string _name):id(_id),text(_text),name(_name)
{
	_answers = 0;
}


question::~question()
{
}

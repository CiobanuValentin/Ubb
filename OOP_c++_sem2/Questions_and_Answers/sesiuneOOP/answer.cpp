#include "answer.h"



answer::answer(int _myId, int _qId, std::string _name, std::string _text, int _votes )
	:myId(_myId),qId(_qId),name(_name),text(_text),votes(_votes)
{

}


answer::~answer()
{
}

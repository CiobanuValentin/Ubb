#include "repository.h"



repository::repository(int x):save(x)
{
	std::ifstream fileIn("questions.txt");
	std::ifstream fileIn2("answers.txt");
	std::string id,id2, text, name,votes;
	while (std::getline(fileIn, id, '#')) {
		std::getline(fileIn, text, '#');
		std::getline(fileIn, name, '#');
		questions.push_back(question(stoi(id), text, name));
		std::getline(fileIn, name);
	}
	while (std::getline(fileIn2, id, '#')) {
		std::getline(fileIn2, id2, '#');
		std::getline(fileIn2, name, '#');
		std::getline(fileIn2, text, '#');
		std::getline(fileIn2, votes, '#');
		for (auto& i : questions)
		{
			if (i.getId() == stoi(id2))
			{
				i.incAnswers();
			}
		}
		answers.push_back(answer(stoi(id),stoi(id2),name,text,stoi(votes)));
		std::getline(fileIn2, name);
	}
}


repository::~repository()
{
	if (save == 1) {
		std::ofstream g("questions.txt");
		std::ofstream g2("answers.txt");
		for (auto i : answers)
		{
			g2 << i.getMyId() << "#" << i.getQuestionId() << "#" << i.getName() << "#" << i.getText() << "#" << i.getVotes() << "#" << std::endl;
		}
		for (auto j : questions)
		{
			g << j.getId() << "#" << j.getText() << "#" << j.getName() << "#" << std::endl;
		}
	}
}

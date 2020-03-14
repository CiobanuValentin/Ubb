#pragma once
#include <vector>
#include "observer.h"
class subject
{
private:
	std::vector<observer*> observers;
public:
	subject();
	void notify()
	{
		for (auto i : observers) i->update();
	}
	void addObserver(observer* o) {
		observers.push_back(o);
	}
	~subject();
};


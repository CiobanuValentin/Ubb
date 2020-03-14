#pragma once
class observer
{
public:
	observer();
	virtual void update() = 0;
	virtual ~observer();
};


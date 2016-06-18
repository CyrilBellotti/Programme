package lorann.contract;

import java.io.IOException;

import lorann.controller.UserOrder;

public interface IOrderPerformed 
{

	void orderPerform(UserOrder userOrder) throws IOException;

}
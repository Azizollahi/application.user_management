package service.management.interfaces;

import domain.management.CardsInfo;
import domain.management.HeaderInfo;

public interface IDashboardPageService {
	CardsInfo findCardsInfo(String resourceName);
	HeaderInfo findHeaderInfo(String resourceName);
}

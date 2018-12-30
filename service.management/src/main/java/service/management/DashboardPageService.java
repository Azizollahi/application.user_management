package service.management;

import domain.management.CardsInfo;
import domain.management.HeaderInfo;
import org.springframework.stereotype.Service;
import service.management.interfaces.IDashboardPageService;
import infrastructure.repository.CardInfoRepository;
import infrastructure.repository.HeaderInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DashboardPageService implements IDashboardPageService {
	private CardInfoRepository cardInfoRepository;
	private HeaderInfoRepository headerInfoRepository;

	@Autowired
	public DashboardPageService(HeaderInfoRepository headerInfoRepository, CardInfoRepository cardInfoRepository){
		this.cardInfoRepository = cardInfoRepository;
		this.headerInfoRepository = headerInfoRepository;
	}

	@Override
	public CardsInfo findCardsInfo(String resourceName) {
		var cardsInfo = new CardsInfo();
		cardsInfo.setCardInfo(cardInfoRepository.findByName(resourceName));
		return cardsInfo;
	}

	@Override
	public HeaderInfo findHeaderInfo(String resourceName) {
		var viewHeaderInfo = headerInfoRepository.findByName(resourceName);
		var	headerInfo = new HeaderInfo();
		headerInfo.setHeaderTitle(viewHeaderInfo.getTitle());
		return headerInfo;
	}
}

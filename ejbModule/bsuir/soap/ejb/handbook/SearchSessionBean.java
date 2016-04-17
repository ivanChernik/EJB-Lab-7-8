
package bsuir.soap.ejb.handbook;

import bsuir.soap.ejb.handbook.entity.DefenitionEntity;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@WebService(serviceName = "HandbookJavaScriptService")
public class SearchSessionBean implements SearchSessionBeanRemote {
	@PersistenceContext(unitName = "HandbookJSPU")
	private EntityManager entityManager;

	@WebMethod(operationName = "searchDefenition")
	public String searchDefenition(String nameDefenition) {
		Query selectQuery = entityManager.createNativeQuery(
				"SELECT defenition FROM \"handBookJS\" WHERE name_defenition = '" + nameDefenition + "'");
		List<String> resultList = selectQuery.getResultList();

		String allDefenitions = "";
		for (String resultDefenition : resultList) {
			allDefenitions = allDefenitions + "<br>" + nameDefenition + " - " + resultDefenition;
		}
		return allDefenitions;
	}

	@WebMethod(operationName = "addDefenition")
	public void addDefenition(String nameDefenition, String bodyDefenition) {
		DefenitionEntity inputDefenition = new DefenitionEntity();
		inputDefenition.setNameDefenition(nameDefenition);
		inputDefenition.setBodyDefenition(bodyDefenition);
		entityManager.persist(inputDefenition);
	}

	@WebMethod(operationName = "updateDefenition")
	public void updateDefenition(String nameDefenition, String bodyDefenition) {
		Query updateQuery = entityManager.createNativeQuery("UPDATE \"handBookJS\" SET defenition = '" + bodyDefenition
				+ "' WHERE name_defenition = '" + nameDefenition + "'");
		updateQuery.executeUpdate();
	}

	@WebMethod(operationName = "deleteDefenition")
	public void deleteDefenition(String nameDefenition) {
		Query deleteQuery = entityManager
				.createNativeQuery("DELETE FROM \"handBookJS\" WHERE name_defenition = '" + nameDefenition + "'");
		deleteQuery.executeUpdate();
	}

}

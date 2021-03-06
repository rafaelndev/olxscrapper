package org.olxscrapper.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.olxscrapper.data.OlxItem;
import org.olxscrapper.util.HibernateConnector;

public class OlxItemDAO {

	Session session = null;
	
	public OlxItemDAO() {
        this.session = HibernateConnector.createSessionFactory().openSession();
	}

	public List<OlxItem> lisItems() {
		Criteria criteria = this.session.createCriteria(OlxItem.class);

		try {
			List<OlxItem> queryList = criteria.list();
			if (queryList != null && queryList.isEmpty()) {
				return null;
			} else {
				return (List<OlxItem>) queryList;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public OlxItem findByChecksum(String checksum) {
		try {
			Criteria criteria = this.session.createCriteria(OlxItem.class)
					.add(Restrictions.eq("checksum", checksum))
					.setMaxResults(1);
			
			OlxItem item = (OlxItem) criteria.uniqueResult();
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public OlxItem add(OlxItem item) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(item);
			transaction.commit();
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

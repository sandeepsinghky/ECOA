package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.CodeLookUpDao;
import nc.dhhs.nccss.acts.ecoa.beans.CodeLookUp;
import nc.dhhs.nccss.acts.ecoa.web.service.CodeLookUpService;

/*
 * @author Phani Konuru
 */

public class CodeLookUpServiceImpl implements CodeLookUpService
{

	protected final Logger	logger	= Logger.getLogger(CodeLookUpServiceImpl.class);

	@Autowired
	private CodeLookUpDao	codeLookUpDao;

	@Transactional(readOnly = true)
	public List<CodeLookUp> getCodeLookup(String lookup) throws SQLException
	{
		return codeLookUpDao.getCodeLookup(lookup);
	}

}

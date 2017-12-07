package com.wph.service.impl;

import java.util.List;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Service;

import com.wph.entities.Terminal;
import com.wph.entities.Terminaltype;
import com.wph.service.TerminalService;

@Service("terminalService")
public class TerminalServiceImpl extends BaseServiceImpl<Terminal> implements TerminalService {

	@Override
	public Terminal getByStatusid(Integer typeid, Integer statusid) {
		String queryString = "from Termianl where typeid = :typeid and statusid = :statusid";
		return (Terminal) getSession().createQuery(queryString).setInteger("typeid", typeid)
				.setInteger("statusid", statusid).uniqueResult();
	}

	public Terminal saveTerminal(Integer id, Integer type, String status) {
		// String sql = "select t.terminaltype_id from terminal t where
		// t.statusid = :id";
		String sql1 = "select t.id from terminal t where t.statusid = :id";
		List<Integer> ids = getSession().createSQLQuery(sql1).addScalar("id", StandardBasicTypes.INTEGER)
				.setInteger("id", id).list();
		if (ids.size() == 0) {
			Terminaltype terminaltype = (Terminaltype) getSession().get(Terminaltype.class, type);
			Terminal terminal = new Terminal();

			String sql2 = "select max(t.id) from terminal t";
			Object ob = getSession().createSQLQuery(sql2).uniqueResult();
			if (ob != null) {
				terminal.setId((Integer) ob + 1);
			} else {
				return null;
			}
			terminal.setStatus(status);
			terminal.setStatusid(id);
			terminal.setTerminaltype(terminaltype);
			save(terminal);
			return terminal;
		} else {
			for (Integer i : ids) {
				Terminal t = get(i);
				if (t.getTerminaltype().getId().equals(type)) {
					return t;
				}
			}
			Terminaltype terminaltype = (Terminaltype) getSession().get(Terminaltype.class, type);
			Terminal terminal = new Terminal();
			terminal.setStatus(status);
			terminal.setStatusid(id);
			terminal.setTerminaltype(terminaltype);

			String sql2 = "select max(t.id) from terminal t";
			Object ob = getSession().createSQLQuery(sql2).uniqueResult();
			if (ob != null) {
				terminal.setId((Integer) ob + 1);
			} else {
				return null;
			}
			save(terminal);
			return terminal;
		}
	}

	@Override
	public Terminal saveTerminal(Integer id, String identifi, Integer type, String status) {
		// String sql = "select t.terminaltype_id from terminal t where
		// t.statusid = :id";
		String sql1 = "select t.id from terminal t where t.statusid = :id";
		List<Integer> ids = getSession().createSQLQuery(sql1).addScalar("id", StandardBasicTypes.INTEGER)
				.setInteger("id", id).list();
		if (ids.size() == 0) {
			Terminaltype terminaltype = (Terminaltype) getSession().get(Terminaltype.class, type);
			Terminal terminal = new Terminal();

			String sql2 = "select max(t.id) from terminal t";
			Object ob = getSession().createSQLQuery(sql2).uniqueResult();
			if (ob != null) {
				terminal.setId((Integer) ob + 1);
			} else {
				return null;
			}
			terminal.setStatus(status);
			terminal.setStatusid(id);
			terminal.setTerminaltype(terminaltype);
			terminal.setIdentification(identifi);
			save(terminal);
			return terminal;
		} else {
			for (Integer i : ids) {
				Terminal t = get(i);
				if (t.getTerminaltype().getId().equals(type)) {
					t.setIdentification(identifi);
					update(t);
					return t;
				}
			}
			Terminaltype terminaltype = (Terminaltype) getSession().get(Terminaltype.class, type);
			Terminal terminal = new Terminal();
			terminal.setStatus(status);
			terminal.setStatusid(id);
			terminal.setTerminaltype(terminaltype);
			terminal.setIdentification(identifi);

			String sql2 = "select max(t.id) from terminal t";
			Object ob = getSession().createSQLQuery(sql2).uniqueResult();
			if (ob != null) {
				terminal.setId((Integer) ob + 1);
			} else {
				return null;
			}
			save(terminal);
			return terminal;
		}
	}

	@Override
	public Integer checkBinding(String indenti) {
		String sql = "select t.statusid from terminal t where t.identification = :id";
		Integer id = (Integer) getSession().createSQLQuery(sql).setString("id", indenti).uniqueResult();
		return id;
	}

}

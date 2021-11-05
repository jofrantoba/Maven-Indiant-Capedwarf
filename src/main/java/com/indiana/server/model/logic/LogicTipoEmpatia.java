package com.indiana.server.model.logic;

import java.util.Collection;

import javax.jdo.PersistenceManager;

import com.indiana.server.model.bean.TipoEmpatia;
import com.indiana.server.model.dao.DaoTipoEmpatia;
import com.indiana.shared.BeanParametro;
import com.indiana.shared.UnknownException;

public class LogicTipoEmpatia {

    private PersistenceManager pm;

    public LogicTipoEmpatia(PersistenceManager pm) {
        this.pm = pm;
    }

    public boolean mantenimiento(BeanParametro parametro)
            throws UnknownException {
        DaoTipoEmpatia dao = new DaoTipoEmpatia(this.pm);
        return dao.mantenimiento(parametro);
    }

    public Object getBean(String id) {
        try {
            DaoTipoEmpatia dao = new DaoTipoEmpatia(this.pm);
            return dao.getBean(id);
        } catch (Exception ex) {
            return null;
        }
    }

    public Object getBeanByCode(String codeTipoEmpatia) throws UnknownException {
        DaoTipoEmpatia dao = new DaoTipoEmpatia(this.pm);
        return dao.getBeanByCode(codeTipoEmpatia);
    }

    public Collection<TipoEmpatia> getListarBean() throws UnknownException {
        DaoTipoEmpatia dao = new DaoTipoEmpatia(this.pm);
        Collection<TipoEmpatia> lista = dao.getListarBean();
        return lista;
    }
}

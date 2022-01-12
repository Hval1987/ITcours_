package autopark.servise.implement;

import autopark.bean.Car;
import autopark.dao.exception.DAOException;
import autopark.servise.DamageService;

import java.util.List;

public class DamageServiceImpl implements DamageService {
    @Override
    public Car findDamage(int id) throws DAOException {

        return null;
    }

    @Override
    public void addDamage(Car car) throws DAOException {

    }

    @Override
    public void deleteDamageById(int id) throws DAOException {

    }

    @Override
    public List<Car> getAllDamages() throws DAOException {
        return null;
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;


public abstract class AbstractStorage implements Storage {

    // тут будет уникальная реализация в потомках
    protected abstract Resume doGet(Object searchKey);
    protected abstract void doSave(Resume r, Object searchKey);
    protected abstract void doUpdate(Resume r, Object searchKey);
    protected abstract void doDelete(Object searchKey);

    protected abstract boolean isExist(Object searchKey);
    protected abstract Object getSearchKey(String uuid);

    // для array нужен индекс, для map нужен ключ. Сделать функцию getSerchKey для поиска колюча и переопределять её в потомках
    // проверка на существование в массиве или в листе уникальные, по этому создадим местод getSearchKey вместо getIndex а в дальнейшем его переопределим
    // обновление тоже уникально - вынесем в отдельный метод doUpdate... итд

    public Resume get(String uuid) {
        Object searchKey = getSearchKey(uuid);
        checkIsExist(uuid);
        return doGet(searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        checkIsNotExist(r.getUuid());
        doSave(r, searchKey);
    }

    public void update(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        checkIsExist(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        checkIsExist(uuid);
        doDelete(searchKey);
    }

    private void checkIsExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
    }

    private void checkIsNotExist(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
    }
}
package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.UserRefreshTokens;
import com.elibrary.LibraLink.repositories.UserRefreshTokenRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UserRefreshTokenService implements UserRefreshTokenRepository {
    @Override
    public <S extends UserRefreshTokens> S save(S entity) {
        return null;
    }

    @Override
    public Optional<UserRefreshTokens> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(UserRefreshTokens entity) {

    }

    /**
     * @param integers
     */
    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    /**
     * @param entities
     */
    @Override
    public void deleteAll(Iterable<? extends UserRefreshTokens> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends UserRefreshTokens> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<UserRefreshTokens> findAll() {
        return List.of();
    }

    /**
     * @param integers
     * @return
     */
    @Override
    public List<UserRefreshTokens> findAllById(Iterable<Integer> integers) {
        return List.of();
    }

    @Override
    public List<UserRefreshTokens> findAll(Sort sort) {
        return List.of();
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<UserRefreshTokens> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends UserRefreshTokens> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends UserRefreshTokens> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends UserRefreshTokens, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    /**
     *
     */
    @Override
    public void flush() {

    }

    /**
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends UserRefreshTokens> S saveAndFlush(S entity) {
        return null;
    }

    /**
     * @param entities
     * @param <S>
     * @return
     */
    @Override
    public <S extends UserRefreshTokens> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    /**
     * @param entities
     */
    @Override
    public void deleteAllInBatch(Iterable<UserRefreshTokens> entities) {

    }

    /**
     * @param integers
     */
    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    /**
     *
     */
    @Override
    public void deleteAllInBatch() {

    }

    /**
     * @param integer
     * @return
     */
    @Override
    public UserRefreshTokens getOne(Integer integer) {
        return null;
    }

    /**
     * @param integer
     * @return
     */
    @Override
    public UserRefreshTokens getById(Integer integer) {
        return null;
    }

    /**
     * @param integer
     * @return
     */
    @Override
    public UserRefreshTokens getReferenceById(Integer integer) {
        return null;
    }

    /**
     * @param example
     * @param <S>
     * @return
     */
    @Override
    public <S extends UserRefreshTokens> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    /**
     * @param example
     * @param <S>
     * @return
     */
    @Override
    public <S extends UserRefreshTokens> List<S> findAll(Example<S> example) {
        return List.of();
    }

    /**
     * @param example
     * @param sort
     * @param <S>
     * @return
     */
    @Override
    public <S extends UserRefreshTokens> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    /**
     * @param example
     * @param pageable
     * @param <S>
     * @return
     */
    @Override
    public <S extends UserRefreshTokens> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }
}

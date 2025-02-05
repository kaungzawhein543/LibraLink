package com.elibrary.LibraLink.services;

import com.elibrary.LibraLink.entities.UserRefreshTokens;
import com.elibrary.LibraLink.repositories.UserRefreshTokenRepository;
import org.springframework.data.domain.Example;
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

    @Override
    public List<UserRefreshTokens> findAll(Sort sort) {
        return List.of();
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
}

package com.ayo.data.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nJ\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lcom/ayo/data/repository/MovieDbRepository;", "", "service", "Lcom/ayo/api/services/MovieDbService;", "sharedPrefs", "Lcom/ayo/data/SharedPrefs;", "gson", "Lcom/google/gson/Gson;", "(Lcom/ayo/api/services/MovieDbService;Lcom/ayo/data/SharedPrefs;Lcom/google/gson/Gson;)V", "addMovieToFavourites", "", "", "movie", "Lcom/ayo/data/model/MovieData;", "getFavouriteMovies", "getMovieDetails", "Lcom/ayo/api/model/MovieApi;", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPopularMovies", "Lcom/ayo/api/model/PopularMovieApi;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeMovieFromFavourites", "data_debug"})
public final class MovieDbRepository {
    private final com.ayo.api.services.MovieDbService service = null;
    private final com.ayo.data.SharedPrefs sharedPrefs = null;
    private final com.google.gson.Gson gson = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> addMovieToFavourites(@org.jetbrains.annotations.NotNull()
    com.ayo.data.model.MovieData movie) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> removeMovieFromFavourites(int id) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMovieDetails(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ayo.api.model.MovieApi> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Set<java.lang.String> getFavouriteMovies() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPopularMovies(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ayo.api.model.PopularMovieApi> p0) {
        return null;
    }
    
    @javax.inject.Inject()
    public MovieDbRepository(@org.jetbrains.annotations.NotNull()
    com.ayo.api.services.MovieDbService service, @org.jetbrains.annotations.NotNull()
    com.ayo.data.SharedPrefs sharedPrefs, @org.jetbrains.annotations.NotNull()
    com.google.gson.Gson gson) {
        super();
    }
}
/**
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.scryfall.api.implementation;

import com.scryfall.api.models.*;
import retrofit2.Retrofit;
import com.scryfall.api.Cards;
import com.google.common.reflect.TypeToken;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import com.scryfall.api.models.Card;

import java.io.IOException;
import java.util.UUID;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Response;
import rx.functions.Func1;
import rx.Observable;

/**
 * An instance of this class provides access to all the operations defined
 * in Cards.
 */
public class CardsImpl implements Cards {
    /** The Retrofit service to perform REST calls. */
    private CardsService service;
    /** The service client containing this operation class. */
    private ScryfallClientImpl client;

    /**
     * Initializes an instance of Cards.
     *
     * @param retrofit the Retrofit instance built from a Retrofit Builder.
     * @param client the instance of the service client containing this operation class.
     */
    public CardsImpl(Retrofit retrofit, ScryfallClientImpl client) {
        this.service = retrofit.create(CardsService.class);
        this.client = client;
    }

    /**
     * The interface defining all the services for Cards to be
     * used by Retrofit to perform actually REST calls.
     */
    interface CardsService {
        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Cards search" })
        @GET("cards/search")
        Observable<Response<ResponseBody>> search(@Query("q") String q, @Query("unique") UniqueStrategy unique, @Query("order") SortOrder order, @Query("dir") SortDirection dir, @Query("include_extras") Boolean includeExtras, @Query("page") Integer page);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Cards getNamed" })
        @GET("cards/named")
        Observable<Response<ResponseBody>> getNamed(@Query("exact") String exact, @Query("fuzzy") String fuzzy, @Query("set") String set, @Query("format") String format, @Query("face") String face, @Query("version") String version, @Query("pretty") Boolean pretty);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Cards autocomplete" })
        @GET("cards/autocomplete")
        Observable<Response<ResponseBody>> autocomplete(@Query("q") String q);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Cards getRandom" })
        @GET("cards/random")
        Observable<Response<ResponseBody>> getRandom();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Cards getByMultiverseId" })
        @GET("cards/multiverse/{id}")
        Observable<Response<ResponseBody>> getByMultiverseId(@Path("id") int id);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Cards getByMtgoId" })
        @GET("cards/mtgo/{id}")
        Observable<Response<ResponseBody>> getByMtgoId(@Path("id") int id);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Cards getByArenaId" })
        @GET("cards/arena/{id}")
        Observable<Response<ResponseBody>> getByArenaId(@Path("id") int id);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Cards getByCodeByNumber" })
        @GET("cards/{code}/{number}")
        Observable<Response<ResponseBody>> getByCodeByNumber(@Path("code") String code, @Path("number") int number);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Cards getById" })
        @GET("cards/{id}")
        Observable<Response<ResponseBody>> getById(@Path("id") UUID id);

    }

    /**
     *
     * @param q the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the CardList object if successful.
     */
    public CardList search(String q) {
        return searchWithServiceResponseAsync(q).toBlocking().single().body();
    }

    /**
     *
     * @param q the String value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<CardList> searchAsync(String q, final ServiceCallback<CardList> serviceCallback) {
        return ServiceFuture.fromResponse(searchWithServiceResponseAsync(q), serviceCallback);
    }

    /**
     *
     * @param q the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the CardList object
     */
    public Observable<CardList> searchAsync(String q) {
        return searchWithServiceResponseAsync(q).map(new Func1<ServiceResponse<CardList>, CardList>() {
            @Override
            public CardList call(ServiceResponse<CardList> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param q the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the CardList object
     */
    public Observable<ServiceResponse<CardList>> searchWithServiceResponseAsync(String q) {
        if (q == null) {
            throw new IllegalArgumentException("Parameter q is required and cannot be null.");
        }
        final UniqueStrategy unique = null;
        final SortOrder order = null;
        final SortDirection dir = null;
        final Boolean includeExtras = null;
        final Integer page = null;
        return service.search(q, unique, order, dir, includeExtras, page)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<CardList>>>() {
                @Override
                public Observable<ServiceResponse<CardList>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<CardList> clientResponse = searchDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    /**
     *
     * @param q the String value
     * @param unique Possible values include: 'cards', 'art', 'prints'
     * @param order Possible values include: 'name', 'set', 'released', 'rarity', 'color', 'usd', 'tix', 'eur', 'cmc', 'power', 'toughness', 'edhrec', 'artist'
     * @param dir Possible values include: 'auto', 'asc', 'desc'
     * @param includeExtras the Boolean value
     * @param page the Integer value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the CardList object if successful.
     */
    public CardList search(String q, UniqueStrategy unique, SortOrder order, SortDirection dir, Boolean includeExtras, Integer page) {
        return searchWithServiceResponseAsync(q, unique, order, dir, includeExtras, page).toBlocking().single().body();
    }

    /**
     *
     * @param q the String value
     * @param unique Possible values include: 'cards', 'art', 'prints'
     * @param order Possible values include: 'name', 'set', 'released', 'rarity', 'color', 'usd', 'tix', 'eur', 'cmc', 'power', 'toughness', 'edhrec', 'artist'
     * @param dir Possible values include: 'auto', 'asc', 'desc'
     * @param includeExtras the Boolean value
     * @param page the Integer value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<CardList> searchAsync(String q, UniqueStrategy unique, SortOrder order, SortDirection dir, Boolean includeExtras, Integer page, final ServiceCallback<CardList> serviceCallback) {
        return ServiceFuture.fromResponse(searchWithServiceResponseAsync(q, unique, order, dir, includeExtras, page), serviceCallback);
    }

    /**
     *
     * @param q the String value
     * @param unique Possible values include: 'cards', 'art', 'prints'
     * @param order Possible values include: 'name', 'set', 'released', 'rarity', 'color', 'usd', 'tix', 'eur', 'cmc', 'power', 'toughness', 'edhrec', 'artist'
     * @param dir Possible values include: 'auto', 'asc', 'desc'
     * @param includeExtras the Boolean value
     * @param page the Integer value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the CardList object
     */
    public Observable<CardList> searchAsync(String q, UniqueStrategy unique, SortOrder order, SortDirection dir, Boolean includeExtras, Integer page) {
        return searchWithServiceResponseAsync(q, unique, order, dir, includeExtras, page).map(new Func1<ServiceResponse<CardList>, CardList>() {
            @Override
            public CardList call(ServiceResponse<CardList> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param q the String value
     * @param unique Possible values include: 'cards', 'art', 'prints'
     * @param order Possible values include: 'name', 'set', 'released', 'rarity', 'color', 'usd', 'tix', 'eur', 'cmc', 'power', 'toughness', 'edhrec', 'artist'
     * @param dir Possible values include: 'auto', 'asc', 'desc'
     * @param includeExtras the Boolean value
     * @param page the Integer value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the CardList object
     */
    public Observable<ServiceResponse<CardList>> searchWithServiceResponseAsync(String q, UniqueStrategy unique, SortOrder order, SortDirection dir, Boolean includeExtras, Integer page) {
        if (q == null) {
            throw new IllegalArgumentException("Parameter q is required and cannot be null.");
        }
        return service.search(q, unique, order, dir, includeExtras, page)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<CardList>>>() {
                @Override
                public Observable<ServiceResponse<CardList>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<CardList> clientResponse = searchDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<CardList> searchDelegate(Response<ResponseBody> response) throws ErrorException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<CardList, ErrorException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<CardList>() { }.getType())
                .registerError(ErrorException.class)
                .build(response);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    public Card getNamed() {
        return getNamedWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Card> getNamedAsync(final ServiceCallback<Card> serviceCallback) {
        return ServiceFuture.fromResponse(getNamedWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<Card> getNamedAsync() {
        return getNamedWithServiceResponseAsync().map(new Func1<ServiceResponse<Card>, Card>() {
            @Override
            public Card call(ServiceResponse<Card> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<ServiceResponse<Card>> getNamedWithServiceResponseAsync() {
        final String exact = null;
        final String fuzzy = null;
        final String set = null;
        final String format = null;
        final String face = null;
        final String version = null;
        final Boolean pretty = null;
        return service.getNamed(exact, fuzzy, set, format, face, version, pretty)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Card>>>() {
                @Override
                public Observable<ServiceResponse<Card>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Card> clientResponse = getNamedDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    /**
     *
     * @param exact the String value
     * @param fuzzy the String value
     * @param set the String value
     * @param format the String value
     * @param face the String value
     * @param version the String value
     * @param pretty the Boolean value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    public Card getNamed(String exact, String fuzzy, String set, String format, String face, String version, Boolean pretty) {
        return getNamedWithServiceResponseAsync(exact, fuzzy, set, format, face, version, pretty).toBlocking().single().body();
    }

    /**
     *
     * @param exact the String value
     * @param fuzzy the String value
     * @param set the String value
     * @param format the String value
     * @param face the String value
     * @param version the String value
     * @param pretty the Boolean value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Card> getNamedAsync(String exact, String fuzzy, String set, String format, String face, String version, Boolean pretty, final ServiceCallback<Card> serviceCallback) {
        return ServiceFuture.fromResponse(getNamedWithServiceResponseAsync(exact, fuzzy, set, format, face, version, pretty), serviceCallback);
    }

    /**
     *
     * @param exact the String value
     * @param fuzzy the String value
     * @param set the String value
     * @param format the String value
     * @param face the String value
     * @param version the String value
     * @param pretty the Boolean value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<Card> getNamedAsync(String exact, String fuzzy, String set, String format, String face, String version, Boolean pretty) {
        return getNamedWithServiceResponseAsync(exact, fuzzy, set, format, face, version, pretty).map(new Func1<ServiceResponse<Card>, Card>() {
            @Override
            public Card call(ServiceResponse<Card> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param exact the String value
     * @param fuzzy the String value
     * @param set the String value
     * @param format the String value
     * @param face the String value
     * @param version the String value
     * @param pretty the Boolean value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<ServiceResponse<Card>> getNamedWithServiceResponseAsync(String exact, String fuzzy, String set, String format, String face, String version, Boolean pretty) {
        return service.getNamed(exact, fuzzy, set, format, face, version, pretty)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Card>>>() {
                @Override
                public Observable<ServiceResponse<Card>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Card> clientResponse = getNamedDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Card> getNamedDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
        return this.client.restClient().responseBuilderFactory().<Card, ErrorException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<Card>() { }.getType())
                .registerError(ErrorException.class)
                .build(response);
    }

    /**
     *
     * @param q the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    public Catalog autocomplete(String q) {
        return autocompleteWithServiceResponseAsync(q).toBlocking().single().body();
    }

    /**
     *
     * @param q the String value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Catalog> autocompleteAsync(String q, final ServiceCallback<Catalog> serviceCallback) {
        return ServiceFuture.fromResponse(autocompleteWithServiceResponseAsync(q), serviceCallback);
    }

    /**
     *
     * @param q the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<Catalog> autocompleteAsync(String q) {
        return autocompleteWithServiceResponseAsync(q).map(new Func1<ServiceResponse<Catalog>, Catalog>() {
            @Override
            public Catalog call(ServiceResponse<Catalog> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param q the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<ServiceResponse<Catalog>> autocompleteWithServiceResponseAsync(String q) {
        if (q == null) {
            throw new IllegalArgumentException("Parameter q is required and cannot be null.");
        }
        return service.autocomplete(q)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Catalog>>>() {
                @Override
                public Observable<ServiceResponse<Catalog>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Catalog> clientResponse = autocompleteDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Catalog> autocompleteDelegate(Response<ResponseBody> response) throws ErrorException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<Catalog, ErrorException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<Catalog>() { }.getType())
                .registerError(ErrorException.class)
                .build(response);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    public Card getRandom() {
        return getRandomWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Card> getRandomAsync(final ServiceCallback<Card> serviceCallback) {
        return ServiceFuture.fromResponse(getRandomWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<Card> getRandomAsync() {
        return getRandomWithServiceResponseAsync().map(new Func1<ServiceResponse<Card>, Card>() {
            @Override
            public Card call(ServiceResponse<Card> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<ServiceResponse<Card>> getRandomWithServiceResponseAsync() {
        return service.getRandom()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Card>>>() {
                @Override
                public Observable<ServiceResponse<Card>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Card> clientResponse = getRandomDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Card> getRandomDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
        return this.client.restClient().responseBuilderFactory().<Card, ErrorException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<Card>() { }.getType())
                .registerError(ErrorException.class)
                .build(response);
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    public Card getByMultiverseId(int id) {
        return getByMultiverseIdWithServiceResponseAsync(id).toBlocking().single().body();
    }

    /**
     *
     * @param id the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Card> getByMultiverseIdAsync(int id, final ServiceCallback<Card> serviceCallback) {
        return ServiceFuture.fromResponse(getByMultiverseIdWithServiceResponseAsync(id), serviceCallback);
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<Card> getByMultiverseIdAsync(int id) {
        return getByMultiverseIdWithServiceResponseAsync(id).map(new Func1<ServiceResponse<Card>, Card>() {
            @Override
            public Card call(ServiceResponse<Card> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<ServiceResponse<Card>> getByMultiverseIdWithServiceResponseAsync(int id) {
        return service.getByMultiverseId(id)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Card>>>() {
                @Override
                public Observable<ServiceResponse<Card>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Card> clientResponse = getByMultiverseIdDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Card> getByMultiverseIdDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
        return this.client.restClient().responseBuilderFactory().<Card, ErrorException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<Card>() { }.getType())
                .registerError(ErrorException.class)
                .build(response);
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    public Card getByMtgoId(int id) {
        return getByMtgoIdWithServiceResponseAsync(id).toBlocking().single().body();
    }

    /**
     *
     * @param id the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Card> getByMtgoIdAsync(int id, final ServiceCallback<Card> serviceCallback) {
        return ServiceFuture.fromResponse(getByMtgoIdWithServiceResponseAsync(id), serviceCallback);
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<Card> getByMtgoIdAsync(int id) {
        return getByMtgoIdWithServiceResponseAsync(id).map(new Func1<ServiceResponse<Card>, Card>() {
            @Override
            public Card call(ServiceResponse<Card> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<ServiceResponse<Card>> getByMtgoIdWithServiceResponseAsync(int id) {
        return service.getByMtgoId(id)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Card>>>() {
                @Override
                public Observable<ServiceResponse<Card>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Card> clientResponse = getByMtgoIdDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Card> getByMtgoIdDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
        return this.client.restClient().responseBuilderFactory().<Card, ErrorException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<Card>() { }.getType())
                .registerError(ErrorException.class)
                .build(response);
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    public Card getByArenaId(int id) {
        return getByArenaIdWithServiceResponseAsync(id).toBlocking().single().body();
    }

    /**
     *
     * @param id the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Card> getByArenaIdAsync(int id, final ServiceCallback<Card> serviceCallback) {
        return ServiceFuture.fromResponse(getByArenaIdWithServiceResponseAsync(id), serviceCallback);
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<Card> getByArenaIdAsync(int id) {
        return getByArenaIdWithServiceResponseAsync(id).map(new Func1<ServiceResponse<Card>, Card>() {
            @Override
            public Card call(ServiceResponse<Card> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param id the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<ServiceResponse<Card>> getByArenaIdWithServiceResponseAsync(int id) {
        return service.getByArenaId(id)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Card>>>() {
                @Override
                public Observable<ServiceResponse<Card>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Card> clientResponse = getByArenaIdDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Card> getByArenaIdDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
        return this.client.restClient().responseBuilderFactory().<Card, ErrorException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<Card>() { }.getType())
                .registerError(ErrorException.class)
                .build(response);
    }

    /**
     *
     * @param code the String value
     * @param number the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    public Card getByCodeByNumber(String code, int number) {
        return getByCodeByNumberWithServiceResponseAsync(code, number).toBlocking().single().body();
    }

    /**
     *
     * @param code the String value
     * @param number the int value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Card> getByCodeByNumberAsync(String code, int number, final ServiceCallback<Card> serviceCallback) {
        return ServiceFuture.fromResponse(getByCodeByNumberWithServiceResponseAsync(code, number), serviceCallback);
    }

    /**
     *
     * @param code the String value
     * @param number the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<Card> getByCodeByNumberAsync(String code, int number) {
        return getByCodeByNumberWithServiceResponseAsync(code, number).map(new Func1<ServiceResponse<Card>, Card>() {
            @Override
            public Card call(ServiceResponse<Card> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param code the String value
     * @param number the int value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<ServiceResponse<Card>> getByCodeByNumberWithServiceResponseAsync(String code, int number) {
        if (code == null) {
            throw new IllegalArgumentException("Parameter code is required and cannot be null.");
        }
        return service.getByCodeByNumber(code, number)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Card>>>() {
                @Override
                public Observable<ServiceResponse<Card>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Card> clientResponse = getByCodeByNumberDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Card> getByCodeByNumberDelegate(Response<ResponseBody> response) throws ErrorException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<Card, ErrorException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<Card>() { }.getType())
                .registerError(ErrorException.class)
                .build(response);
    }

    /**
     *
     * @param id the UUID value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Card object if successful.
     */
    public Card getById(UUID id) {
        return getByIdWithServiceResponseAsync(id).toBlocking().single().body();
    }

    /**
     *
     * @param id the UUID value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Card> getByIdAsync(UUID id, final ServiceCallback<Card> serviceCallback) {
        return ServiceFuture.fromResponse(getByIdWithServiceResponseAsync(id), serviceCallback);
    }

    /**
     *
     * @param id the UUID value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<Card> getByIdAsync(UUID id) {
        return getByIdWithServiceResponseAsync(id).map(new Func1<ServiceResponse<Card>, Card>() {
            @Override
            public Card call(ServiceResponse<Card> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @param id the UUID value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Card object
     */
    public Observable<ServiceResponse<Card>> getByIdWithServiceResponseAsync(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Parameter id is required and cannot be null.");
        }
        return service.getById(id)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Card>>>() {
                @Override
                public Observable<ServiceResponse<Card>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Card> clientResponse = getByIdDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Card> getByIdDelegate(Response<ResponseBody> response) throws ErrorException, IOException, IllegalArgumentException {
        return this.client.restClient().responseBuilderFactory().<Card, ErrorException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<Card>() { }.getType())
                .registerError(ErrorException.class)
                .build(response);
    }

}

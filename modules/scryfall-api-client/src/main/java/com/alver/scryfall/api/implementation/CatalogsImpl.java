/**
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.alver.scryfall.api.implementation;

import com.alver.fatefall.api.models.Catalog;
import com.alver.scryfall.api.Catalogs;
import com.alver.scryfall.api.models.ErrorException;
import retrofit2.Retrofit;
import com.google.common.reflect.TypeToken;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.Response;
import rx.functions.Func1;
import rx.Observable;

/**
 * An instance of this class provides access to all the operations defined
 * in Catalogs.
 */
public class CatalogsImpl implements Catalogs {
    /** The Retrofit service to perform REST calls. */
    private CatalogsService service;
    /** The service client containing this operation class. */
    private ScryfallClientImpl client;

    /**
     * Initializes an instance of Catalogs.
     *
     * @param retrofit the Retrofit instance built from a Retrofit Builder.
     * @param client the instance of the service client containing this operation class.
     */
    public CatalogsImpl(Retrofit retrofit, ScryfallClientImpl client) {
        this.service = retrofit.create(CatalogsService.class);
        this.client = client;
    }

    /**
     * The interface defining all the services for Catalogs to be
     * used by Retrofit to perform actually REST calls.
     */
    interface CatalogsService {
        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Catalogs getCardNames" })
        @GET("catalog/card-names")
        Observable<Response<ResponseBody>> getCardNames();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Catalogs getWordBank" })
        @GET("catalog/word-bank")
        Observable<Response<ResponseBody>> getWordBank();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Catalogs getCreatureTypes" })
        @GET("catalog/creature-types")
        Observable<Response<ResponseBody>> getCreatureTypes();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Catalogs getPlaneswalkerTypes" })
        @GET("catalog/planeswalker-types")
        Observable<Response<ResponseBody>> getPlaneswalkerTypes();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Catalogs getLandTypes" })
        @GET("catalog/land-types")
        Observable<Response<ResponseBody>> getLandTypes();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Catalogs getArtifactTypes" })
        @GET("catalog/artifact-types")
        Observable<Response<ResponseBody>> getArtifactTypes();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Catalogs getEnchantmentTypes" })
        @GET("catalog/enchantment-types")
        Observable<Response<ResponseBody>> getEnchantmentTypes();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Catalogs getSpellTypes" })
        @GET("catalog/spell-types")
        Observable<Response<ResponseBody>> getSpellTypes();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Catalogs getPowers" })
        @GET("catalog/powers")
        Observable<Response<ResponseBody>> getPowers();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Catalogs getToughnesses" })
        @GET("catalog/thoughnesses")
        Observable<Response<ResponseBody>> getToughnesses();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Catalogs getLoyalties" })
        @GET("catalog/loyalties")
        Observable<Response<ResponseBody>> getLoyalties();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: com.scryfall.api.Catalogs getWatermarks" })
        @GET("catalog/watermarks")
        Observable<Response<ResponseBody>> getWatermarks();

    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Catalog object if successful.
     */
    public Catalog getCardNames() {
        return getCardNamesWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Catalog> getCardNamesAsync(final ServiceCallback<Catalog> serviceCallback) {
        return ServiceFuture.fromResponse(getCardNamesWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<Catalog> getCardNamesAsync() {
        return getCardNamesWithServiceResponseAsync().map(new Func1<ServiceResponse<Catalog>, Catalog>() {
            @Override
            public Catalog call(ServiceResponse<Catalog> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<ServiceResponse<Catalog>> getCardNamesWithServiceResponseAsync() {
        return service.getCardNames()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Catalog>>>() {
                @Override
                public Observable<ServiceResponse<Catalog>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Catalog> clientResponse = getCardNamesDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Catalog> getCardNamesDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
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
     * @return the Catalog object if successful.
     */
    public Catalog getWordBank() {
        return getWordBankWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Catalog> getWordBankAsync(final ServiceCallback<Catalog> serviceCallback) {
        return ServiceFuture.fromResponse(getWordBankWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<Catalog> getWordBankAsync() {
        return getWordBankWithServiceResponseAsync().map(new Func1<ServiceResponse<Catalog>, Catalog>() {
            @Override
            public Catalog call(ServiceResponse<Catalog> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<ServiceResponse<Catalog>> getWordBankWithServiceResponseAsync() {
        return service.getWordBank()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Catalog>>>() {
                @Override
                public Observable<ServiceResponse<Catalog>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Catalog> clientResponse = getWordBankDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Catalog> getWordBankDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
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
     * @return the Catalog object if successful.
     */
    public Catalog getCreatureTypes() {
        return getCreatureTypesWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Catalog> getCreatureTypesAsync(final ServiceCallback<Catalog> serviceCallback) {
        return ServiceFuture.fromResponse(getCreatureTypesWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<Catalog> getCreatureTypesAsync() {
        return getCreatureTypesWithServiceResponseAsync().map(new Func1<ServiceResponse<Catalog>, Catalog>() {
            @Override
            public Catalog call(ServiceResponse<Catalog> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<ServiceResponse<Catalog>> getCreatureTypesWithServiceResponseAsync() {
        return service.getCreatureTypes()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Catalog>>>() {
                @Override
                public Observable<ServiceResponse<Catalog>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Catalog> clientResponse = getCreatureTypesDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Catalog> getCreatureTypesDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
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
     * @return the Catalog object if successful.
     */
    public Catalog getPlaneswalkerTypes() {
        return getPlaneswalkerTypesWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Catalog> getPlaneswalkerTypesAsync(final ServiceCallback<Catalog> serviceCallback) {
        return ServiceFuture.fromResponse(getPlaneswalkerTypesWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<Catalog> getPlaneswalkerTypesAsync() {
        return getPlaneswalkerTypesWithServiceResponseAsync().map(new Func1<ServiceResponse<Catalog>, Catalog>() {
            @Override
            public Catalog call(ServiceResponse<Catalog> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<ServiceResponse<Catalog>> getPlaneswalkerTypesWithServiceResponseAsync() {
        return service.getPlaneswalkerTypes()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Catalog>>>() {
                @Override
                public Observable<ServiceResponse<Catalog>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Catalog> clientResponse = getPlaneswalkerTypesDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Catalog> getPlaneswalkerTypesDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
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
     * @return the Catalog object if successful.
     */
    public Catalog getLandTypes() {
        return getLandTypesWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Catalog> getLandTypesAsync(final ServiceCallback<Catalog> serviceCallback) {
        return ServiceFuture.fromResponse(getLandTypesWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<Catalog> getLandTypesAsync() {
        return getLandTypesWithServiceResponseAsync().map(new Func1<ServiceResponse<Catalog>, Catalog>() {
            @Override
            public Catalog call(ServiceResponse<Catalog> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<ServiceResponse<Catalog>> getLandTypesWithServiceResponseAsync() {
        return service.getLandTypes()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Catalog>>>() {
                @Override
                public Observable<ServiceResponse<Catalog>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Catalog> clientResponse = getLandTypesDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Catalog> getLandTypesDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
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
     * @return the Catalog object if successful.
     */
    public Catalog getArtifactTypes() {
        return getArtifactTypesWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Catalog> getArtifactTypesAsync(final ServiceCallback<Catalog> serviceCallback) {
        return ServiceFuture.fromResponse(getArtifactTypesWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<Catalog> getArtifactTypesAsync() {
        return getArtifactTypesWithServiceResponseAsync().map(new Func1<ServiceResponse<Catalog>, Catalog>() {
            @Override
            public Catalog call(ServiceResponse<Catalog> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<ServiceResponse<Catalog>> getArtifactTypesWithServiceResponseAsync() {
        return service.getArtifactTypes()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Catalog>>>() {
                @Override
                public Observable<ServiceResponse<Catalog>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Catalog> clientResponse = getArtifactTypesDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Catalog> getArtifactTypesDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
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
     * @return the Catalog object if successful.
     */
    public Catalog getEnchantmentTypes() {
        return getEnchantmentTypesWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Catalog> getEnchantmentTypesAsync(final ServiceCallback<Catalog> serviceCallback) {
        return ServiceFuture.fromResponse(getEnchantmentTypesWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<Catalog> getEnchantmentTypesAsync() {
        return getEnchantmentTypesWithServiceResponseAsync().map(new Func1<ServiceResponse<Catalog>, Catalog>() {
            @Override
            public Catalog call(ServiceResponse<Catalog> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<ServiceResponse<Catalog>> getEnchantmentTypesWithServiceResponseAsync() {
        return service.getEnchantmentTypes()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Catalog>>>() {
                @Override
                public Observable<ServiceResponse<Catalog>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Catalog> clientResponse = getEnchantmentTypesDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Catalog> getEnchantmentTypesDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
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
     * @return the Catalog object if successful.
     */
    public Catalog getSpellTypes() {
        return getSpellTypesWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Catalog> getSpellTypesAsync(final ServiceCallback<Catalog> serviceCallback) {
        return ServiceFuture.fromResponse(getSpellTypesWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<Catalog> getSpellTypesAsync() {
        return getSpellTypesWithServiceResponseAsync().map(new Func1<ServiceResponse<Catalog>, Catalog>() {
            @Override
            public Catalog call(ServiceResponse<Catalog> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<ServiceResponse<Catalog>> getSpellTypesWithServiceResponseAsync() {
        return service.getSpellTypes()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Catalog>>>() {
                @Override
                public Observable<ServiceResponse<Catalog>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Catalog> clientResponse = getSpellTypesDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Catalog> getSpellTypesDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
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
     * @return the Catalog object if successful.
     */
    public Catalog getPowers() {
        return getPowersWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Catalog> getPowersAsync(final ServiceCallback<Catalog> serviceCallback) {
        return ServiceFuture.fromResponse(getPowersWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<Catalog> getPowersAsync() {
        return getPowersWithServiceResponseAsync().map(new Func1<ServiceResponse<Catalog>, Catalog>() {
            @Override
            public Catalog call(ServiceResponse<Catalog> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<ServiceResponse<Catalog>> getPowersWithServiceResponseAsync() {
        return service.getPowers()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Catalog>>>() {
                @Override
                public Observable<ServiceResponse<Catalog>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Catalog> clientResponse = getPowersDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Catalog> getPowersDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
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
     * @return the Catalog object if successful.
     */
    public Catalog getToughnesses() {
        return getToughnessesWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Catalog> getToughnessesAsync(final ServiceCallback<Catalog> serviceCallback) {
        return ServiceFuture.fromResponse(getToughnessesWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<Catalog> getToughnessesAsync() {
        return getToughnessesWithServiceResponseAsync().map(new Func1<ServiceResponse<Catalog>, Catalog>() {
            @Override
            public Catalog call(ServiceResponse<Catalog> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<ServiceResponse<Catalog>> getToughnessesWithServiceResponseAsync() {
        return service.getToughnesses()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Catalog>>>() {
                @Override
                public Observable<ServiceResponse<Catalog>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Catalog> clientResponse = getToughnessesDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Catalog> getToughnessesDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
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
     * @return the Catalog object if successful.
     */
    public Catalog getLoyalties() {
        return getLoyaltiesWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Catalog> getLoyaltiesAsync(final ServiceCallback<Catalog> serviceCallback) {
        return ServiceFuture.fromResponse(getLoyaltiesWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<Catalog> getLoyaltiesAsync() {
        return getLoyaltiesWithServiceResponseAsync().map(new Func1<ServiceResponse<Catalog>, Catalog>() {
            @Override
            public Catalog call(ServiceResponse<Catalog> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<ServiceResponse<Catalog>> getLoyaltiesWithServiceResponseAsync() {
        return service.getLoyalties()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Catalog>>>() {
                @Override
                public Observable<ServiceResponse<Catalog>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Catalog> clientResponse = getLoyaltiesDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Catalog> getLoyaltiesDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
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
     * @return the Catalog object if successful.
     */
    public Catalog getWatermarks() {
        return getWatermarksWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Catalog> getWatermarksAsync(final ServiceCallback<Catalog> serviceCallback) {
        return ServiceFuture.fromResponse(getWatermarksWithServiceResponseAsync(), serviceCallback);
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<Catalog> getWatermarksAsync() {
        return getWatermarksWithServiceResponseAsync().map(new Func1<ServiceResponse<Catalog>, Catalog>() {
            @Override
            public Catalog call(ServiceResponse<Catalog> response) {
                return response.body();
            }
        });
    }

    /**
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Catalog object
     */
    public Observable<ServiceResponse<Catalog>> getWatermarksWithServiceResponseAsync() {
        return service.getWatermarks()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Catalog>>>() {
                @Override
                public Observable<ServiceResponse<Catalog>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Catalog> clientResponse = getWatermarksDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Catalog> getWatermarksDelegate(Response<ResponseBody> response) throws ErrorException, IOException {
        return this.client.restClient().responseBuilderFactory().<Catalog, ErrorException>newInstance(this.client.serializerAdapter())
                .register(200, new TypeToken<Catalog>() { }.getType())
                .registerError(ErrorException.class)
                .build(response);
    }

}

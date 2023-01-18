# DOCKER KULLANIMI

## IMAGE OLUŞTURMA
    docker build --build-arg JAR_FILE=<build path> -t <image name> .
    docker build --build-arg JAR_FILE=auth-service/build/libs/auth-service-v.0.0.1.jar -t java4authservice:v003 .
    build path: servisimizden build aldığımız zaman oluşan jar dosyasının konumu.
    image name: oluşturacağımız image e vereceğimiz isim (versiyon numarası vermeyi unutmamak lazım )

## IMAGE UZERINDEN CONTAINER CALISTIRMA
    docker run -d -p disport:icport image_ismi:version
    docker run -d -p 9091:8090 java4authservice:v001
    icport: application yml da uygulamanın ayağa kalktığı port
    disport: containerın dışarıya açıldığı port, istekler bu porta gelecek bu port iç porta yonlendirecek

## NETWORK OLUSTURMA
    docker network ls ->> var olan networklerimizi listeleme.
    docker network rm somenetwork : network adıyla networkumuzu silme.

    docker network create --driver bridge --subnet <ag portları> --gateway 182.18.0.1 <network name>
    docker network create --driver bridge --subnet 182.18.0.1/24 --gateway 182.18.0.1 java4-network

    ag portları: networkumuzdeki ip aralığını belirlediğimiz yer.
    network create komutu ile bir network olusturabiliriz.

### NETWORK'E CONTAINER BAGLAMA 
    docker run --name java4-postgresql -e POSTGRES_PASSWORD=root --net java4-network -d -p 5656:5432 postgres
    java4-postgresql adında bir postgresql container'i olusturduk.
    --net java4-network komutu ile olusturdugumuz java-network 'une postgresqlimizi bagladik.
    daha sonra application.yml'da db_url imizi değiştirdik. 
    jdbc:postgresql://localhost:5432/SocialMediaDB yerine artık
    jdbc:postgresql://java4-postgresql/SocialMediaDB yazdık.
    localhost yerine aslında olusturdugumuz postgresql containerının ismini verdik.
    ve pg adminden register ile 5656 daki postgresqlimize bağlandık ve SocialMediaDB adında bir database oluşturduk.
    daha sonra uygulamamızı tekrar build edip, uygulamamızdan bir image olusturduk.
    docker build --build-arg JAR_FILE=auth-service/build/libs/auth-service-v.0.0.1.jar -t java4authservice:v003 .
    ve bu image'i çalıştırırken olusturdugumuz java4-networkune asagıdaki kodla bagladik.
    docker run --net java4-network -d -p 9091:8090 java4authservice:v003






    
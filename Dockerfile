FROM amineaffane/node-java

# Node canvas requirements
RUN apt-get update && apt-get install -y libcairo2-dev libjpeg62-turbo-dev libpango1.0-dev libgif-dev build-essential g++

# Add podigg-lc
ADD podigg-lc /evalrun
RUN cd /evalrun && npm install

# Add and build Java wrapper
ADD src /tmp/build/src
ADD pom.xml /tmp/build
RUN cd /tmp/build && mvn package -Dmaven.test.skip=true
RUN cd /tmp/build && cp target/podigg-0.0.1-SNAPSHOT.jar /evalrun/podigg.jar
RUN rm -rf /tmp/build

WORKDIR /evalrun

CMD java -cp podigg.jar org.hobbit.core.run.ComponentStarter org.hobbit.podigg.PodiggGenerator

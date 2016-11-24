FROM amineaffane/node-java

ADD podigg-lc /evalrun
ADD target/podigg-0.0.1-SNAPSHOT.jar /evalrun/podigg.jar
RUN cd /evalrun/podigg && npm install && npm link
RUN cd /evalrun && npm link podigg && npm install

WORKDIR /evalrun

CMD java -cp podigg.jar org.hobbit.core.run.ComponentStarter org.hobbit.podigg.PodiggGenerator

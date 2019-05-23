source set_var.sh

#mvn -DskipTests -Dmaven.test.skip=true clean install -pl tools
#mvn -DskipTests -Dmaven.test.skip=true clean install -pl gui
mvn -DskipTests -Dmaven.test.skip=true clean install

#cp "/lfs2/wikidata-query-rdf-2/tools/target/wikidata-query-tools-0.3.1-SNAPSHOT-jar-with-dependencies.jar" "/lfs2/wikidata-query-rdf-2/dist/target/service-0.3.1-SNAPSHOT/lib/wikidata-query-tools-0.3.1-SNAPSHOT-jar-with-dependencies.jar"

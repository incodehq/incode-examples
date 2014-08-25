isis-module-excel
========================

[![Build Status](https://travis-ci.org/isiaddons/isis-isisaddons-excel.png?branch=master)](https://travis-ci.org/isisaddons/isis-module-excel)


**OUT OF DATE, TO BE UPDATED**


Integrates with [Apache Isis](http://isis/apache.org)', providing a domain service so that a collection of (view model) object scan be exported to an Excel spreadsheet, or recreated by importing from Excel.  The underlying technology used is [Apache POI](http://poi.apache.org).

See also the [Excel wicket extension](https://github.com/isisaddons/isis-wicket-excel), which makes every collection downloadable as an Excel spreadsheet.


## API & Implementation ##

The API exposed by `ExcelService` is:

    public interface ExcelService {

        public static class Exception extends RuntimeException { ... }
        
        @Programmatic
        public <T> Blob toExcel(
            final List<T> domainObjects, 
            final Class<T> cls, final 
            String fileName) 
            throws ExcelService.Exception;

        @Programmatic
        public <T extends ViewModel> List<T> fromExcel(
            final Blob excelBlob, 
            final Class<T> cls) 
            throws ExcelService.Exception;
    }

The class that implements this API is `com.danhaywood.isis.domainservice.excel.impl.ExcelServiceImpl`.    

## Usage ##

Given:

    public class ToDoItemExportImportLineItem extends AbstractViewModel { ... }

which are wrappers around `ToDoItem` entities:

    final List<ToDoItem> items = ...;
    final List<ToDoItemExportImportLineItem> toDoItemViewModels = 
        Lists.transform(items, 
            new Function<ToDoItem, ToDoItemExportImportLineItem>(){
                @Override
                public ToDoItemExportImportLineItem apply(final ToDoItem toDoItem) {
                    return container.newViewModelInstance(
                        ToDoItemExportImportLineItem.class, 
                        bookmarkService.bookmarkFor(toDoItem).getIdentifier());
                }
            });

then the following creates an Isis `Blob` (bytestream) containing the spreadsheet of these view models:

    return excelService.toExcel(toDoItemViewModels, ToDoItemExportImportLineItem.class, fileName);

and conversely:

    Blob spreadsheet = ...;
    List<ToDoItemExportImportLineItem> lineItems = 
        excelService.fromExcel(spreadsheet, ToDoItemExportImportLineItem.class);

recreates view models from a spreadsheet.

## Maven Configuration

In the root `pom.xml`, add:

    <dependency>
        <groupId>com.danhaywood.isis.domainservice</groupId>
        <artifactId>danhaywood-isis-domainservice-excel</artifactId>
        <version>x.y.z</version>
        <type>pom</type>
        <scope>import</scope>
    </dependency>

where `x.y.z` currently is 1.4.0-SNAPSHOT (though the plan is to release this code into the [Maven Central Repo](http://search.maven.org/#search|ga|1|isis-domainservice-excel)).

In the `pom.xml` for your "dom" module, add:
    
    <dependency>
        <groupId>com.danhaywood.isis.domainservice</groupId>
        <artifactId>danhaywood-isis-domainservice-excel-applib</artifactId>
    </dependency>

In the `pom.xml` for your "webapp" module, add:

    <dependency>
        <groupId>com.danhaywood.isis.domainservice</groupId>
        <artifactId>danhaywood-isis-domainservice-excel-impl</artifactId>
    </dependency>

## Registering the service

In the `WEB-INF\isis.properties` file, add:

    isis.services = ...,\
                    # Excel domain service, \
                    com.danhaywood.isis.domainservice.excel.impl.ExcelServiceImpl,\
                    ...

## Legal Stuff

#### License ####

    Copyright 2013~2014 Dan Haywood

    Licensed under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.

#### Dependencies ####

This module depends upon the following:

    <dependencies>
        <dependency>
            <!-- ASL v2.0 -->
            <groupId>org.apache.isis.viewer</groupId>
            <artifactId>isis-viewer-wicket-ui</artifactId>
            <version>${isis-viewer-wicket.version}</version>
        </dependency>

        <dependency>
            <!-- ASL v2.0 -->
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>${poi.version}</version>
        </dependency>
        <dependency>
            <!-- ASL v2.0 -->
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>${poi.version}</version>
        </dependency>
        <dependency>
            <!-- ASL v2.0 -->
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml-schemas</artifactId>
            <version>${poi.version}</version>
        </dependency>        
    </dependencies>

##  Maven deploy notes

Only the `dom` module is deployed, and is done so using Sonatype's OSS support (see 
[user guide](http://central.sonatype.org/pages/apache-maven.html)).

#### Release to Sonatype's Snapshot Repo ####

To deploy a snapshot, use:

    pushd dom
    mvn clean deploy
    popd

The artifacts should be available in Sonatype's 
[Snapshot Repo](https://oss.sonatype.org/content/repositories/snapshots).

#### Release to Maven Central (scripted process) ####

The `release.sh` script automates the release process.  It performs the following:

* perform sanity check (`mvn clean install -o`) that everything builds ok
* bump the `pom.xml` to a specified release version, and tag
* perform a double check (`mvn clean install -o`) that everything still builds ok
* release the code using `mvn clean deploy`
* bump the `pom.xml` to a specified release version

For example:

    sh release.sh 1.6.1 1.6.2-SNAPSHOT dan@haywood-associates.co.uk "this is not really my passphrase"
    
where
* `$1` is the release version
* `$2` is the snapshot version
* `$3` is the email of the secret key (`~/.gnupg/secring.gpg`) to use for signing
* `$4` is the corresponding passphrase for that secret key.

If the script completes successfully, then push changes:

    git push
    
If the script fails to complete, then identify the cause, perform a `git reset --hard` to start over and fix the issue
before trying again.

#### Release to Maven Central (manual process) ####

If you don't want to use `release.sh`, then the steps can be performed manually.

To start, call `bumpver.sh` to bump up to the release version, eg:

     `sh bumpver.sh 1.6.1`

which:
* edit the parent `pom.xml`, to change `${isis-module-command.version}` to version
* edit the `dom` module's pom.xml version
* commit the changes
* if a SNAPSHOT, then tag

Next, do a quick sanity check:

    mvn clean install -o
    
All being well, then release from the `dom` module:

    pushd dom
    mvn clean deploy -P release \
        -Dpgp.secretkey=keyring:id=dan@haywood-associates.co.uk \
        -Dpgp.passphrase="literal:this is not really my passphrase"
    popd

where (for example):
* "dan@haywood-associates.co.uk" is the email of the secret key (`~/.gnupg/secring.gpg`) to use for signing
* the pass phrase is as specified as a literal

Other ways of specifying the key and passphrase are available, see the `pgp-maven-plugin`'s 
[documentation](http://kohsuke.org/pgp-maven-plugin/secretkey.html)).

If (in the `dom`'s `pom.xml` the `nexus-staging-maven-plugin` has the `autoReleaseAfterClose` setting set to `true`,
then the above command will automatically stage, close and the release the repo.  Sync'ing to Maven Central should 
happen automatically.  According to Sonatype's guide, it takes about 10 minutes to sync, but up to 2 hours to update 
[search](http://search.maven.org).

If instead the `autoReleaseAfterClose` setting is set to `false`, then the repo will require manually closing and 
releasing either by logging onto the [Sonatype's OSS staging repo](https://oss.sonatype.org) or alternatively by 
releasing from the command line using `mvn nexus-staging:release`.

Finally, don't forget to update the release to next snapshot, eg:

    sh bumpver.sh 1.6.2-SNAPSHOT

and then push changes.

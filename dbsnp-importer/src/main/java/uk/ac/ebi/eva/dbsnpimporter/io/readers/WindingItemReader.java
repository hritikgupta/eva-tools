/*
 * Copyright 2017 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.ebi.eva.dbsnpimporter.io.readers;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;

import java.util.ArrayList;
import java.util.List;

/**
 * The winding reader takes a reader that returns an element in each read call and groups them together in a single
 * read call. This class can be used with any kind of item reader, beware that some subtypes of ItemReaders like
 * {@link ItemStreamReader} require special operations before and after read operations. If your reader extends
 * {@link ItemStreamReader} please use {@link WindingItemStreamReader}
 *
 * @param <T>
 */
public class WindingItemReader<T> implements ItemReader<List<T>> {

    private final ItemReader<T> reader;

    private boolean alreadyConsumed;

    public WindingItemReader(ItemReader<T> reader) {
        this.reader = reader;
        this.alreadyConsumed = false;
    }

    @Override
    public List<T> read() throws Exception {
        if (alreadyConsumed) {
            return null;
        } else {
            List<T> items = new ArrayList<>();
            T item;

            while ((item = reader.read()) != null) {
                items.add(item);
            }
            alreadyConsumed = true;
            return items;
        }
    }

    protected ItemReader<T> getReader() {
        return reader;
    }
}

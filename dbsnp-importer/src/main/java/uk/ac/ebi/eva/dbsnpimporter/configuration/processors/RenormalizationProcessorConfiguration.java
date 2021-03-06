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
package uk.ac.ebi.eva.dbsnpimporter.configuration.processors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import uk.ac.ebi.eva.dbsnpimporter.contig.ContigMapping;
import uk.ac.ebi.eva.dbsnpimporter.jobs.steps.processors.RefseqToGenbankMappingProcessor;
import uk.ac.ebi.eva.dbsnpimporter.jobs.steps.processors.RenormalizationProcessor;
import uk.ac.ebi.eva.dbsnpimporter.parameters.Parameters;
import uk.ac.ebi.eva.dbsnpimporter.io.FastaSequenceReader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class RenormalizationProcessorConfiguration {

    private FastaSequenceReader fastaSequenceReader;

    @Bean
    RenormalizationProcessor renormalizationProcessor(Parameters parameters) throws IOException {
        Path referenceFastaFile = Paths.get(parameters.getReferenceFastaFile());
        fastaSequenceReader = new FastaSequenceReader(referenceFastaFile);
        return new RenormalizationProcessor(fastaSequenceReader);
    }
}

// chunk.pipe.ts
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'chunk',
})
export class ChunkPipe implements PipeTransform {
  transform(array: any[], size: number): any[] {
    if (!array.length) return [];
    return Array.from({ length: Math.ceil(array.length / size) }, (_, index) =>
      array.slice(index * size, index * size + size)
    );
  }
}

import Combine
import SwiftUI
import shared


class ObservableBreedModel: ObservableObject {
    private var viewModel: BreedCallbackViewModel?

    @Published
    var loading = false

    @Published
    var photo: NSArray<AnyObject>?

    @Published
    var error: String? // todo make this into actual object impl, should not be string.

    private var cancellables = [AnyCancellable]()

    func activate() {
        let viewModel = BreedCallbackViewModel()

        doPublish(viewModel.iosPhotos) { [weak self] photoState in
            self?.loading = photoState.isLoading
            
            self?.error = photoState.error.debugDescription
            
            if (photoState.isSuccessContent()) {
                let data = (photoState.response as? ResultSuccess<NSArray>?)
                let data2 = data??.data
                self?.photos = data2
                self?.error = nil
            } else {
                self?.photos = nil
                self?.error = photoState.error.debugDescription
            }

        }.store(in: &cancellables)

        self.viewModel = viewModel
    }

    func deactivate() {
        cancellables.forEach { $0.cancel() }
        cancellables.removeAll()

        viewModel?.clear()
        viewModel = nil
    }
}

struct BreedListScreen: View {
    @StateObject
    var observableModel = ObservableBreedModel()

    var body: some View {
        BreedListContent(loading: observableModel.loading, photos: observableModel.photos, error: observableModel.error)
        .onAppear(perform: {
            observableModel.activate()
        })
        .onDisappear(perform: {
            observableModel.deactivate()
        })
    }
}

struct BreedListContent: View {
    var loading: Bool
    var photos: Result<NSArray>?
    var error: String?

    var body: some View {
        ZStack {
            VStack {
                if let photos = photos {
                    List(breeds, id: \.id) { breed in
                        BreedRowView(breed: breed) {
                            onBreedFavorite(breed)
                        }
                    }
                }
                if let error = error {
                    Text(error)
                        .foregroundColor(.red)
                }
                Button("Refresh") {
                    refresh()
                }
            }
            if loading { Text("Loading...") }
        }
    }
}

struct BreedRowView: View {
    var breed: Breed
    var onTap: () -> Void

    var body: some View {
        Button(action: onTap) {
            HStack {
                Text(breed.name)
                    .padding(4.0)
                Spacer()
                Image(systemName: (!breed.favorite) ? "heart" : "heart.fill")
                    .padding(4.0)
            }
        }
    }
}

//struct BreedListScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        BreedListContent(
//            loading: false,
//            breeds: [
//                Breed(id: 0, name: "appenzeller", favorite: false),
//                Breed(id: 1, name: "australian", favorite: true)
//            ],
//            error: nil,
//            onBreedFavorite: { _ in },
//            refresh: {}
//        )
//    }
//}

/// Create a Combine publisher from the supplied `FlowAdapter`. Use this in contexts where more transformation will be
/// done on the Swift side before the value is bound to UI
func createPublisher<T>(_ flowAdapter: FlowAdapter<T>) -> AnyPublisher<T, KotlinError> {
    return Deferred<Publishers.HandleEvents<PassthroughSubject<T, KotlinError>>> {
        let subject = PassthroughSubject<T, KotlinError>()
        let canceller = flowAdapter.subscribe(
            onEach: { item in subject.send(item) },
            onComplete: { subject.send(completion: .finished) },
            onThrow: { error in subject.send(completion: .failure(KotlinError(error))) }
        )
        return subject.handleEvents(receiveCancel: { canceller.cancel() })
    }.eraseToAnyPublisher()
}

/// Prepare the supplied `FlowAdapter` to be bound to UI. The `onEach` callback will be called from `DispatchQueue.main`
/// on every new emission.
///
/// Note that this calls `assertNoFailure()` internally so you should handle errors upstream to avoid crashes.
open func doPublish<T>(_ flowAdapter: FlowAdapter<T>, onEach: @escaping (T) -> Void) -> Cancellable {
    return createPublisher(flowAdapter)
        .assertNoFailure()
        .compactMap { $0 }
        .receive(on: DispatchQueue.main)
        .sink { onEach($0) }
}

/// Wraps a `KotlinThrowable` in a `LocalizedError` which can be used as  a Combine error type
class KotlinError: LocalizedError {
    let throwable: KotlinThrowable

    init(_ throwable: KotlinThrowable) {
        self.throwable = throwable
    }
    var errorDescription: String? {
        throwable.message
    }
}
